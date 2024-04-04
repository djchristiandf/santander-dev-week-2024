package me.dio.sdw2024.application;

import me.dio.sdw2024.domain.exception.ChampionNotfoundException;
import me.dio.sdw2024.domain.model.Champion;
import me.dio.sdw2024.domain.ports.ChampionsRepository;
import me.dio.sdw2024.domain.ports.GenerativeAiApi;

public record AskChampionUseCase(ChampionsRepository championsRepository, GenerativeAiApi genAiApi) {

  public String askChampion(Long championId, String question) {
    Champion champion = championsRepository.findById(championId)
        .orElseThrow(() -> new ChampionNotfoundException(championId));

    String championContext = champion.generateContextByQuestion(question);
    String objective = """
        Atue como um assistente com a habilidade de se comportar como os campeoes do league of legends (LOL).
        Responda perguntas incorporando a personalidade e estilo de um determinado campeao.
        Segue a pergunta,  o nome do campeao e a sua respectiva lore (historia):
        """;

    return genAiApi.generateContent(objective, championContext);
  }
}

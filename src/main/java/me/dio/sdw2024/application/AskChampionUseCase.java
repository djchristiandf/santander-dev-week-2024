package me.dio.sdw2024.application;

import me.dio.sdw2024.domain.exception.ChampionNotfoundException;
import me.dio.sdw2024.domain.model.Champion;
import me.dio.sdw2024.domain.ports.ChampionsRepository;

public record AskChampionUseCase(ChampionsRepository championsRepository) {

  public String askChampion(Long championId, String question) {
    Champion champion = championsRepository.findById(championId)
        .orElseThrow(() -> new ChampionNotfoundException(championId));

    String championContext = champion.generateContextByQuestion(question);
    return championContext;
  }
}

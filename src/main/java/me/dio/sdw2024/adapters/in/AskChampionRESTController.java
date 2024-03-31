package me.dio.sdw2024.adapters.in;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; // Corrigido
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.sdw2024.application.AskChampionUseCase;

@Tag(name = "champions", description = "Endpoints do dominio Ask Champion")
@RestController
@RequestMapping(value = "/champions")
public record AskChampionRESTController(AskChampionUseCase askQuestionUseCase) {

  @CrossOrigin
  @PostMapping("/{championId}/ask")
  public AskChampionResponse askChampion(@PathVariable("championId") Long championId,
      @RequestBody AskChampionRequest request) {
    String answer = askQuestionUseCase.askChampion(championId, request.question());
    return new AskChampionResponse(answer);
  }

  public record AskChampionRequest(String question) {
  }

  public record AskChampionResponse(String answer) {
  }
}

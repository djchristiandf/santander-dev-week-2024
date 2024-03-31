package me.dio.sdw2024.adapters.in;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.sdw2024.application.ListChampionsUseCase;
import me.dio.sdw2024.domain.model.Champion;

@Tag(name = "champions", description = "Endpoints para listagem de campeões")
@RestController
@RequestMapping(value = "/champions")
public record ListChampionsRESTController(ListChampionsUseCase listChampionsUseCase) {
  @GetMapping
  public List<Champion> findAllChampions() {
    return listChampionsUseCase.findAll();
  }
}

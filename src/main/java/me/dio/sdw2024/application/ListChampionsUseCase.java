package me.dio.sdw2024.application;

import java.util.List;

import me.dio.sdw2024.domain.model.Champion;
import me.dio.sdw2024.domain.ports.ChampionsRepository;

public record ListChampionsUseCase(ChampionsRepository championsRepository) {
  public List<Champion> findAll() {
    return championsRepository.findAll();
  }
}

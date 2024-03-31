package me.dio.sdw2024.domain.ports;

import java.util.List;
import java.util.Optional;

import me.dio.sdw2024.domain.model.Champion;

public interface ChampionsRepository {

  List<Champion> findAll();

  Optional<Champion> findById(Long id);
}

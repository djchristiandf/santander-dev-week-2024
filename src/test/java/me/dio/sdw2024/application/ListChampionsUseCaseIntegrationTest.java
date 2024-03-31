package me.dio.sdw2024.application;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import me.dio.sdw2024.domain.model.Champion;

@SpringBootTest
public class ListChampionsUseCaseIntegrationTest {

  @Autowired
  private ListChampionsUseCase listChampionsUseCase;

  @Test
  public void testListChampions() {
    List<Champion> champions = listChampionsUseCase.findAll();
    assert !champions.isEmpty() : "The list of champions is empty";

    Champion firstChampion = champions.get(0);
    assert firstChampion != null : "First champion is null";

    Assertions.assertEquals(12, champions.size());
  }
}

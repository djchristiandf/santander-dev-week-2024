package me.dio.sdw2024.application;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import me.dio.sdw2024.domain.model.Champions;

@SpringBootTest
public class ListChampionsUseCaseIntegrationTest {

  @Autowired
  private ListChampionsUseCase listChampionsUseCase;

  @Test
  public void testListChampions() {
    List<Champions> champions = listChampionsUseCase.findAll();
    assert !champions.isEmpty() : "The list of champions is empty";

    Champions firstChampion = champions.get(0);
    assert firstChampion != null : "First champion is null";

    Assertions.assertEquals(12, champions.size());
  }
}

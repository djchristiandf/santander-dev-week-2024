package me.dio.sdw2024.domain.exception;

public class ChampionNotfoundException extends RuntimeException {

  public ChampionNotfoundException(Long championId) {
    super("Champion %d  not found".formatted(championId));
  }
}

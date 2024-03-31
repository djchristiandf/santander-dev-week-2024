package me.dio.sdw2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import me.dio.sdw2024.application.AskChampionUseCase;
import me.dio.sdw2024.application.ListChampionsUseCase;
import me.dio.sdw2024.domain.ports.ChampionsRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public AskChampionUseCase provideAskChampionsUseCase(ChampionsRepository championsRepository) {
		return new AskChampionUseCase(championsRepository);
	}

	@Bean
	public ListChampionsUseCase provideListChampionsUseCase(ChampionsRepository championsRepository) {
		return new ListChampionsUseCase(championsRepository);
	}

}

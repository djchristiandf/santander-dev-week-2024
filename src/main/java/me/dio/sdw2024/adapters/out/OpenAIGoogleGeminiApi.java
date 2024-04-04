package me.dio.sdw2024.adapters.out;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;

import feign.FeignException;
import feign.RequestInterceptor;
import me.dio.sdw2024.domain.ports.GenerativeAiApi;

/**
 * GeminiApi
 */
@ConditionalOnProperty(name = "generative-ai.provider", havingValue = "GEMINI")
@FeignClient(name = "GeminiApiService", url = "${gemini.base-url}", configuration = OpenAIGoogleGeminiApi.Config.class)
public interface OpenAIGoogleGeminiApi extends GenerativeAiApi {

  @PostMapping("/v1beta/models/gemini-pro:generateContent")
  GoogleGeminiResp chatCompletion(GoogleGeminiReq req);

  @Override
  default String generateContent(String objective, String context) {
    String prompt = """
        %s
        %s
        """.formatted(objective, context);

    GoogleGeminiReq req = new GoogleGeminiReq(
        List.of(new Content(List.of(new Part(prompt)))));
    try {
      GoogleGeminiResp resp = chatCompletion(req);
      return resp.candidates().getFirst().content().parts().getFirst().text();
    } catch (FeignException httpErrors) {
      return "Deu ruim! Erro de comunicação com a API do Google Gemini.";
    } catch (Exception unexpectedError) {
      return "Deu mais ruim ainda! O retorno da API do Google Gemini não contem os dados esperados.";
    }
  }

  record GoogleGeminiReq(List<Content> contents) {
  }

  record Content(List<Part> parts) {
  }

  record Part(String text) {
  }

  record GoogleGeminiResp(List<Candidate> candidates) {
  }

  record Candidate(Content content) {
  }

  class Config {
    @Bean
    public RequestInterceptor apiKeyRequestInterceptor(@Value("${gemini.api-key}") String apiKey) {
      return requestTemplate -> requestTemplate.query("key", apiKey);
    }
  }
}
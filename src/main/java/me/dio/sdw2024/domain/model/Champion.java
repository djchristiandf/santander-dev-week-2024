package me.dio.sdw2024.domain.model;

public record Champion(
                Long id,
                String name,
                String role,
                String lore,
                String imageUrl) {
        public String generateContextByQuestion(String question) {
                return """
                                   Pergunta: %s
                                   Nome do campeao: %s
                                   Funcao: %s
                                   Lore (Historia): %s
                                """.formatted(question, this.name, this.role, this.lore);
        }
}

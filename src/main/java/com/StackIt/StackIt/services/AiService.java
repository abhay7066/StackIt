package com.StackIt.StackIt.services;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

@Service
public class AiService {


    @Value("${gemini.api.key}")
    String apiKey;

    @Value("${gemini.api.url}")
    String apiUrl;


    WebClient webClient;

    AiService() {
        webClient = WebClient.builder().build();
    }

    public boolean generateReply(String content) {
        String prompt = getPrompt(content);
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        String response = webClient.post()
                .uri(apiUrl + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(20))
                .onErrorResume(e -> Mono.error(new Exception("Something went wrong, Unable to process: " + e.getMessage())))
                .block();

        System.out.println(extractResponseContent(response));
        return "Yes".equals(extractResponseContent(response).trim());
    }

    private String extractResponseContent(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            return rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong, unable to process");
        }


    }

    public String getPrompt(String content) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("You are a content moderator for a Q&A platform like Stack Overflow. ")
                .append("Your job is to evaluate the following user-submitted content and determine if it is safe to display publicly. ")
                .append("Specifically, check whether the content contains any offensive, explicit, harmful, abusive, or inappropriate language. ")
                .append("Return only one word: 'Yes' if the content is clean and safe to publish, or 'No' if the content is harmful, inappropriate, or uncertain. ")
                .append("If you are unsure, return 'No' to allow manual review by an admin.\n\n")
                .append("Content:\n")
                .append(content)
                .append("\n\nAnswer with only 'Yes' or 'No'.");
        return prompt.toString();
    }

}

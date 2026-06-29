package com.reeshma.resumeanalyzer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class AIService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public AIService(RestClient restClient, ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.objectMapper = objectMapper;
    }

    public String generateSummary(String resumeText) {

        try {

            String prompt = """
                    You are an ATS Resume Analyzer.

                    Analyze the following resume and generate a professional summary in 3-4 lines.

                    Resume:
                    """ + resumeText;

            Map<String, Object> requestBody = Map.of(
                    "contents", List.of(
                            Map.of(
                                    "parts", List.of(
                                            Map.of("text", prompt)
                                    )
                            )
                    )
            );

            String response = restClient.post()
                    .uri("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey)
                    .body(requestBody)
                    .retrieve()
                    .body(String.class);

            JsonNode root = objectMapper.readTree(response);

            return root.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

        } catch (Exception e) {

            return "Unable to generate AI summary.";
        }
    }
}
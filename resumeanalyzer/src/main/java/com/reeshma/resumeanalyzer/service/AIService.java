package com.reeshma.resumeanalyzer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
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

    // ===========================
    // Generic Gemini API Method
    // ===========================
    private String askGemini(String prompt) {

        try {

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
            return "Unable to connect to Gemini AI.";
        }
    }

    // ===========================
    // AI Resume Summary
    // ===========================
    public String generateSummary(String resumeText) {

        String prompt = """
                You are a professional ATS Resume Reviewer.

                Analyze the following resume.

                Generate a concise professional summary in exactly 3-4 lines.

                Focus on:
                - Education
                - Technical Skills
                - Projects
                - Career Objective

                Return only the summary.

                Resume:
                """ + resumeText;

        return askGemini(prompt);
    }

    // ===========================
    // AI Resume Suggestions
    // ===========================
    public List<String> generateSuggestions(String resumeText) {

        String prompt = """
                You are an ATS Resume Expert.

                Analyze the following resume.

                Suggest exactly 5 improvements.

                Rules:
                - One suggestion per line.
                - No numbering.
                - No explanation.
                - Keep each suggestion short.

                Resume:
                """ + resumeText;

        String response = askGemini(prompt);

        return Arrays.stream(response.split("\\n"))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .toList();
    }

}
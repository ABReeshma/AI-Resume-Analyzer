package com.reeshma.resumeanalyzer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reeshma.resumeanalyzer.dto.CareerRecommendationResponse;
import com.reeshma.resumeanalyzer.dto.ProjectReviewResponse;
import com.reeshma.resumeanalyzer.dto.SuggestionsResponse;
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

            String text = root.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

            text = text.replace("```json", "")
                    .replace("```", "")
                    .trim();

            return text;

        } catch (Exception e) {
            return "Unable to connect to Gemini AI.";
        }
    }

    // ===========================
    // AI Resume Summary
    // ===========================
    public String generateSummary(String resumeText) {

        String prompt = """
You are an ATS Resume Expert.

Generate a professional resume summary.

Rules:
- Maximum 60 words.
- Mention education, technical skills and projects.
- ATS-friendly.
- Professional tone.
- No markdown.
- Return only the summary.

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

            Analyze the resume.

            Return ONLY valid JSON.

            {
              "suggestions":[]
            }

            Rules:
            - Exactly 5 suggestions.
            - Each suggestion under 12 words.
            - Start each suggestion with an action verb.
            - ATS-friendly.
            - No numbering.
            - No explanation.
            - No markdown.
            - Return JSON only.

            Resume:
            """ + resumeText;

        try {

            String jsonResponse = askGemini(prompt);

            SuggestionsResponse response =
                    objectMapper.readValue(
                            jsonResponse,
                            SuggestionsResponse.class
                    );

            return response.getSuggestions();

        } catch (Exception e) {

            e.printStackTrace();

            return List.of(
                    "Unable to generate AI suggestions."
            );

        }
    }
    public ProjectReviewResponse generateProjectReview(String projectsSection) {

        String prompt = """
You are a Senior Software Engineer.

Review ONLY the projects from this resume.

Return ONLY valid JSON.

{
  "overallRating":"",
  "projectComplexity":"",
  "strengths":[],
  "improvements":[]
}

Rules:
- overallRating between 1/5 and 5/5.
- projectComplexity must be Basic, Intermediate or Advanced.
- Exactly 3 strengths.
- Exactly 3 improvements.
- Each point under 15 words.
- ATS-friendly.
- No markdown.
- Return JSON only.

Projects:
""" + projectsSection;

        try {

            String jsonResponse = askGemini(prompt);

            return objectMapper.readValue(
                    jsonResponse,
                    ProjectReviewResponse.class
            );

        } catch (Exception e) {

            return new ProjectReviewResponse(
                    "N/A",
                    List.of("Unable to analyze projects."),
                    List.of("Try again.")
            );

        }

    }
    public CareerRecommendationResponse generateCareerRecommendation(String resumeText) {

        String prompt = """
You are a Technical Recruiter.

Analyze the resume.

Recommend exactly 3 software engineering roles.

Return ONLY valid JSON.

{
  "recommendedRoles":[],
  "reason":""
}

Rules:
- Recommend only software engineering roles.
- Do not use Junior, Fresher or Entry-Level.
- Exactly 3 roles.
- Reason under 25 words.
- ATS-friendly.
- No markdown.
- Return JSON only.

Resume:
""" + resumeText;

        try {

            String jsonResponse = askGemini(prompt);

            return objectMapper.readValue(
                    jsonResponse,
                    CareerRecommendationResponse.class
            );

        } catch (Exception e) {

            return new CareerRecommendationResponse(
                    List.of("Unable to recommend roles"),
                    "AI recommendation unavailable."
            );

        }
    }

}
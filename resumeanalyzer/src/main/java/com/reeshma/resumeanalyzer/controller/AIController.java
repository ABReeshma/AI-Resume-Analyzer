package com.reeshma.resumeanalyzer.controller;

import com.reeshma.resumeanalyzer.service.AIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/ai/test")
    public String testAI() {

        return aiService.generateSummary(
                "Java Spring Boot React MySQL REST API JWT Authentication"
        );
    }
}
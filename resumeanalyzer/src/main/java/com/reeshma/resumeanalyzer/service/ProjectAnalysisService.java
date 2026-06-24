package com.reeshma.resumeanalyzer.service;

import org.springframework.stereotype.Service;

@Service
public class ProjectAnalysisService {

    public String extractProjectsSection(String resumeText) {

        String text = resumeText.toLowerCase();

        int startIndex = text.indexOf("projects");

        int endIndex = text.indexOf("technical skills");

        if (startIndex != -1 &&
                endIndex != -1 &&
                endIndex > startIndex) {

            return resumeText.substring(startIndex, endIndex);
        }

        return "";
    }

    public int calculateProjectScore(String projectsSection) {

        int projectCount = 0;

        String text = projectsSection.toLowerCase();

        if (text.contains("skillswap")) {
            projectCount++;
        }

        if (text.contains("uplift")) {
            projectCount++;
        }

        if (projectCount == 1) {
            return 10;
        } else if (projectCount == 2) {
            return 20;
        } else if (projectCount >= 3) {
            return 25;
        }

        return 0;
    }
}
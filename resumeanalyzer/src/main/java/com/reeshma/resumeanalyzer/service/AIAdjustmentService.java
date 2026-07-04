package com.reeshma.resumeanalyzer.service;

import com.reeshma.resumeanalyzer.dto.AIAdjustmentResponse;
import com.reeshma.resumeanalyzer.dto.ProjectReviewResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AIAdjustmentService {

    public AIAdjustmentResponse calculateAIAdjustment(
            String summary,
            ProjectReviewResponse review,
            int certificateScore
    ) {

        int scoreImpact = 0;
        List<String> reasons = new ArrayList<>();

        // Strong AI Summary
        if (summary != null && summary.length() > 150) {
            scoreImpact += 2;
            reasons.add("Strong professional summary (+2)");
        }

        // Project Rating
        if (review != null && review.getOverallRating() != null) {

            String rating = review.getOverallRating();

            if (rating.startsWith("5")) {
                scoreImpact += 3;
                reasons.add("Excellent project quality (+3)");
            }
            else if (rating.startsWith("4")) {
                scoreImpact += 2;
                reasons.add("Strong project quality (+2)");
            }
            else if (rating.startsWith("3")) {
                scoreImpact += 1;
                reasons.add("Good project quality (+1)");
            }

        }

        // AI Project Improvements
        if (review != null && review.getImprovements() != null) {

            for (String improvement : review.getImprovements()) {

                String text = improvement.toLowerCase();

                if (text.contains("docker")) {
                    scoreImpact--;
                    reasons.add("Docker deployment missing (-1)");
                }

                if (text.contains("deployment")) {
                    scoreImpact--;
                    reasons.add("Project deployment missing (-1)");
                }

                if (text.contains("unit")) {
                    scoreImpact--;
                    reasons.add("Unit testing missing (-1)");
                }

                if (text.contains("ci/cd")) {
                    scoreImpact--;
                    reasons.add("CI/CD pipeline missing (-1)");
                }

                if (text.contains("quantified")) {
                    scoreImpact--;
                    reasons.add("Quantified achievements missing (-1)");
                }

            }

        }

        // Certificates
        if (certificateScore == 0) {
            scoreImpact -= 2;
            reasons.add("No certifications found (-2)");
        }

        return new AIAdjustmentResponse(
                scoreImpact,
                reasons
        );
    }

}
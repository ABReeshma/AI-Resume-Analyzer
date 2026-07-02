package com.reeshma.resumeanalyzer.service;

import com.reeshma.resumeanalyzer.dto.ProjectReviewResponse;
import org.springframework.stereotype.Service;

@Service
public class AIAdjustmentService {

    public int calculateAIAdjustment(String summary,
                                     ProjectReviewResponse review,
                                     int certificateScore) {

        int adjustment = 0;

        // Strong summary
        if (summary.length() > 150) {
            adjustment += 2;
        }

        // Good Project Rating
        if (review.getOverallRating().startsWith("5")) {
            adjustment += 3;
        } else if (review.getOverallRating().startsWith("4")) {
            adjustment += 2;
        } else if (review.getOverallRating().startsWith("3")) {
            adjustment += 1;
        }

        // Improvements suggested
        for (String improvement : review.getImprovements()) {

            String text = improvement.toLowerCase();

            if (text.contains("docker"))
                adjustment--;

            if (text.contains("deployment"))
                adjustment--;

            if (text.contains("unit test"))
                adjustment--;

            if (text.contains("ci/cd"))
                adjustment--;

        }

        // No certificates
        if (certificateScore == 0) {
            adjustment -= 2;
        }

        return adjustment;
    }

}
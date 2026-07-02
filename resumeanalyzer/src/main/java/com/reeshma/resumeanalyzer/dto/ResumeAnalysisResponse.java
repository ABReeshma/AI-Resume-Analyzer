package com.reeshma.resumeanalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResumeAnalysisResponse {

    private List<String> detectedSkills;

    private int atsScore;

    private int totalSkillWeight;

    private int projectScore;

    private int skillScore;

    private int educationScore;

    private int certificateScore;

    private int formatScore;

    private String aiSummary;

    private List<String> aiSuggestions;

    private ProjectReviewResponse projectReview;

    private int aiAdjustment;
}
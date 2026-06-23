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
}
package com.reeshma.resumeanalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CareerRecommendationResponse {

    private List<String> recommendedRoles;

    private String reason;

}
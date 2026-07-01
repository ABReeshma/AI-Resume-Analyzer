package com.reeshma.resumeanalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectReviewResponse {

    private String overallRating;

    private List<String> strengths;

    private List<String> improvements;

}
package com.reeshma.resumeanalyzer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ResumeAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String candidateName;

    private String email;

    private int atsScore;

    private String detectedSkills;

    private int skillScore;

    private int projectScore;

    private int educationScore;

    private int certificateScore;

    private int formatScore;

    private LocalDateTime analysisDate;
}
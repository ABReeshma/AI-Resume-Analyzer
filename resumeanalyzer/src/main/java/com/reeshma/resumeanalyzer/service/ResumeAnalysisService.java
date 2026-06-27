package com.reeshma.resumeanalyzer.service;

import com.reeshma.resumeanalyzer.entity.ResumeAnalysis;
import com.reeshma.resumeanalyzer.repository.ResumeAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResumeAnalysisService {

    @Autowired
    private ResumeAnalysisRepository repository;

    public void saveAnalysis(String resumeText,
                             int atsScore,
                             int skillScore,
                             List<String> detectedSkills,
                             int projectScore,
                             int educationScore,
                             int certificateScore,
                             int formatScore) {

        ResumeAnalysis analysis = new ResumeAnalysis();

        analysis.setCandidateName(extractCandidateName(resumeText));
        analysis.setEmail(extractEmail(resumeText));

        analysis.setAtsScore(atsScore);
        analysis.setSkillScore(skillScore);
        analysis.setDetectedSkills(
                String.join(", ", detectedSkills)
        );
        analysis.setProjectScore(projectScore);
        analysis.setEducationScore(educationScore);
        analysis.setCertificateScore(certificateScore);
        analysis.setFormatScore(formatScore);

        analysis.setAnalysisDate(LocalDateTime.now());

        repository.save(analysis);
    }

    private String extractCandidateName(String resumeText) {

        String[] lines = resumeText.split("\\R");

        for (String line : lines) {

            line = line.trim();

            if (!line.isEmpty()) {
                return line;
            }
        }

        return "Unknown";
    }

    private String extractEmail(String resumeText) {

        java.util.regex.Pattern pattern =
                java.util.regex.Pattern.compile(
                        "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");

        java.util.regex.Matcher matcher =
                pattern.matcher(resumeText);

        if (matcher.find()) {
            return matcher.group();
        }

        return "Not Found";
    }
}
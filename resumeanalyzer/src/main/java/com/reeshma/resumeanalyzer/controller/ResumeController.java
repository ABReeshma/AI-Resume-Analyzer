package com.reeshma.resumeanalyzer.controller;

import com.reeshma.resumeanalyzer.dto.ProjectReviewResponse;
import com.reeshma.resumeanalyzer.entity.Resume;
import com.reeshma.resumeanalyzer.service.*;
import com.reeshma.resumeanalyzer.utils.PdfParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.reeshma.resumeanalyzer.dto.ResumeAnalysisResponse;

import java.util.List;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private SkillExtractionService skillExtractionService;

    @Autowired
    private ATSScoreService atsScoreService;

    @Autowired
    private ProjectAnalysisService projectAnalysisService;

    @Autowired
    private EducationAnalysisService educationAnalysisService;

    @Autowired
    private CertificateAnalysisService certificateAnalysisService;

    @Autowired
    private ResumeFormatService resumeFormatService;

    private final AIAdjustmentService aiAdjustmentService;

    @Autowired
    private ResumeAnalysisService resumeAnalysisService;

    @Autowired
    private AIService aiService;

    public ResumeController(AIAdjustmentService aiAdjustmentService) {
        this.aiAdjustmentService = aiAdjustmentService;
    }

    @PostMapping("/save")
    public Resume saveResume(@RequestBody Resume resume) {
        return resumeService.saveResume(resume);
    }

    @GetMapping("/all")
    public List<Resume> getAllResume(){
        return resumeService.getAllResume();
    }

    @PostMapping("/upload")
    public ResumeAnalysisResponse uploadResume(
            @RequestParam("file") MultipartFile file) {

        try {

            // Extract text from PDF
            String resumeText = PdfParserUtil.extractText(file);

            // Detect skills
            List<String> detectedSkills =
                    skillExtractionService.extractSkills(resumeText);

            // Calculate total skill weight
            int totalSkillWeight =
                    atsScoreService.calculateTotalWeight(detectedSkills);

            // Extract projects section
            String projectsSection =
                    projectAnalysisService.extractProjectsSection(resumeText);

            // Calculate project score
            int projectScore =
                    projectAnalysisService.calculateProjectScore(projectsSection);

            int skillScore =
                    atsScoreService.calculateSkillScore(totalSkillWeight);

            int educationScore =
                    educationAnalysisService.calculateEducationScore(resumeText);

            int certificateScore =
                    certificateAnalysisService.calculateCertificateScore(resumeText);

            int formatScore =
                    resumeFormatService.calculateFormatScore(resumeText);

            String aiSummary = aiService.generateSummary(resumeText);

            List<String> suggestions =
                    aiService.generateSuggestions(resumeText);

            ProjectReviewResponse projectReview =
                    aiService.generateProjectReview(projectsSection);

            int aiAdjustment =
                    aiAdjustmentService.calculateAIAdjustment(
                            aiSummary,
                            projectReview,
                            certificateScore
                    );

            int atsScore =
                    atsScoreService.calculateFinalATSScore(
                            skillScore,
                            projectScore,
                            educationScore,
                            certificateScore,
                            formatScore,
                            aiAdjustment
                    );
            resumeAnalysisService.saveAnalysis(
                    resumeText,
                    atsScore,
                    skillScore,
                    detectedSkills,
                    projectScore,
                    educationScore,
                    certificateScore,
                    formatScore
            );
            return new ResumeAnalysisResponse(
                    detectedSkills,
                    atsScore,
                    totalSkillWeight,
                    projectScore,
                    skillScore,
                    educationScore,
                    certificateScore,
                    formatScore,
                    aiSummary,
                    suggestions,
                    projectReview,
                    aiAdjustment
            );

        } catch (Exception e) {

            return new ResumeAnalysisResponse(
                    List.of("Error : " + e.getMessage()),
                    0,
                    0,
                    0,
                    0,
                    0,0,0,"",
                    List.of(),
                    new ProjectReviewResponse(
                            "N/A",
                            List.of(),
                            List.of()
                    ),0
            );
        }
    }
}
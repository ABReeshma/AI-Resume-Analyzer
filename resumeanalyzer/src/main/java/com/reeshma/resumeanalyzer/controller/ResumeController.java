package com.reeshma.resumeanalyzer.controller;

import com.reeshma.resumeanalyzer.entity.Resume;
import com.reeshma.resumeanalyzer.service.ResumeService;
import com.reeshma.resumeanalyzer.service.SkillExtractionService;
import com.reeshma.resumeanalyzer.utils.PdfParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.reeshma.resumeanalyzer.dto.ResumeAnalysisResponse;
import com.reeshma.resumeanalyzer.service.ATSScoreService;

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

            String resumeText = PdfParserUtil.extractText(file);

            List<String> detectedSkills =
                    skillExtractionService.extractSkills(resumeText);

            int totalSkillWeight =
                    atsScoreService.calculateTotalWeight(detectedSkills);

            int atsScore =
                    atsScoreService.calculateATSScore(totalSkillWeight);

            return new ResumeAnalysisResponse(
                    detectedSkills,
                    atsScore,
                    totalSkillWeight
            );

        } catch (Exception e) {

            return new ResumeAnalysisResponse(
                    List.of("Error : " + e.getMessage()),
                    0,
                    0
            );
        }
    }

}
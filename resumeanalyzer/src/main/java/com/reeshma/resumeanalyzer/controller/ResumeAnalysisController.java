package com.reeshma.resumeanalyzer.controller;

import com.reeshma.resumeanalyzer.entity.ResumeAnalysis;
import com.reeshma.resumeanalyzer.repository.ResumeAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResumeAnalysisController {

    @Autowired
    private ResumeAnalysisRepository repository;

    @GetMapping("/analysis/history")
    public List<ResumeAnalysis> getHistory() {

        return repository.findAll();
    }
}
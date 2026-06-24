package com.reeshma.resumeanalyzer.service;

import org.springframework.stereotype.Service;

@Service
public class ResumeFormatService {

    public int calculateFormatScore(String resumeText) {

        int score = 0;

        String text = resumeText.toLowerCase();

        if(text.contains("education")) score += 2;
        if(text.contains("projects")) score += 2;
        if(text.contains("technical skills")) score += 2;
        if(text.contains("languages")) score += 2;
        if(text.contains("email")) score += 2;

        return score;
    }
}
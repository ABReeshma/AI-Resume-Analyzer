package com.reeshma.resumeanalyzer.service;

import org.springframework.stereotype.Service;

@Service
public class CertificateAnalysisService {

    public int calculateCertificateScore(String resumeText) {

        String text = resumeText.toLowerCase();

        int count = 0;

        if(text.contains("aws")) count++;
        if(text.contains("oracle")) count++;
        if(text.contains("nptel")) count++;
        if(text.contains("coursera")) count++;
        if(text.contains("udemy")) count++;

        return Math.min(count * 2, 10);
    }
}
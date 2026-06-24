package com.reeshma.resumeanalyzer.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EducationAnalysisService {

    public int calculateEducationScore(String resumeText) {

        Pattern pattern = Pattern.compile("(\\d+\\.\\d+)");
        Matcher matcher = pattern.matcher(resumeText);

        while (matcher.find()) {

            double value = Double.parseDouble(matcher.group());

            if (value <= 10) {

                if (value >= 9.0) return 15;
                if (value >= 8.0) return 12;
                if (value >= 7.0) return 10;
                if (value >= 6.0) return 8;

                return 5;
            }
        }

        return 5;
    }
}
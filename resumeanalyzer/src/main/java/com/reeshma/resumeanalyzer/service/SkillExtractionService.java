package com.reeshma.resumeanalyzer.service;

import com.reeshma.resumeanalyzer.entity.SkillMaster;
import com.reeshma.resumeanalyzer.repository.SkillMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillExtractionService {

    @Autowired
    private SkillMasterRepository skillMasterRepository;

    public List<String> extractSkills(String resumeText) {

        List<SkillMaster> skills = skillMasterRepository.findAll();

        List<String> detectedSkills = new ArrayList<>();

        for (SkillMaster skill : skills) {

            if (resumeText.toLowerCase()
                    .contains(skill.getSkillName().toLowerCase())) {

                detectedSkills.add(skill.getSkillName());
            }
        }

        return detectedSkills;
    }
}
package com.reeshma.resumeanalyzer.service;

import com.reeshma.resumeanalyzer.entity.SkillMaster;
import com.reeshma.resumeanalyzer.repository.SkillMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ATSScoreService {

    @Autowired
    private SkillMasterRepository skillMasterRepository;

    public int calculateTotalWeight(List<String> detectedSkills) {

        List<SkillMaster> allSkills = skillMasterRepository.findAll();

        int totalWeight = 0;

        for (SkillMaster skill : allSkills) {

            if (detectedSkills.contains(skill.getSkillName())) {

                totalWeight += skill.getWeight();
            }
        }

        return totalWeight;
    }

    public int calculateSkillScore(int totalWeight) {

        if (totalWeight >= 120) {
            return 40;
        } else if (totalWeight >= 100) {
            return 35;
        } else if (totalWeight >= 80) {
            return 30;
        } else if (totalWeight >= 60) {
            return 25;
        } else if (totalWeight >= 40) {
            return 20;
        } else {
            return 10;
        }
    }
    public int calculateFinalATSScore(
            int skillScore,
            int projectScore,
            int educationScore,
            int certificateScore,
            int formatScore) {

        return skillScore
                + projectScore
                + educationScore
                + certificateScore
                + formatScore;
    }
}
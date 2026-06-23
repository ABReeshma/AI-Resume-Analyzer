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

    public int calculateATSScore(int totalWeight) {

        if (totalWeight >= 90) {
            return 100;
        } else if (totalWeight >= 70) {
            return 85;
        } else if (totalWeight >= 50) {
            return 70;
        } else if (totalWeight >= 30) {
            return 55;
        } else {
            return 30;
        }
    }
}
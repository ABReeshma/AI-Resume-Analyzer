package com.reeshma.resumeanalyzer.service;

import com.reeshma.resumeanalyzer.entity.SkillMaster;
import com.reeshma.resumeanalyzer.repository.SkillMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillMasterService {

    @Autowired
    private SkillMasterRepository skillMasterRepository;

    public List<SkillMaster> getAllSkills() {
        return skillMasterRepository.findAll();
    }
}
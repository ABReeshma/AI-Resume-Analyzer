package com.reeshma.resumeanalyzer.controller;

import com.reeshma.resumeanalyzer.entity.SkillMaster;
import com.reeshma.resumeanalyzer.service.SkillMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SkillMasterController {

    @Autowired
    private SkillMasterService skillMasterService;

    @GetMapping("/skills")
    public List<SkillMaster> getAllSkills() {

        return skillMasterService.getAllSkills();
    }
}
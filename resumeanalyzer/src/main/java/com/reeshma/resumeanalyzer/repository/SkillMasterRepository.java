package com.reeshma.resumeanalyzer.repository;

import com.reeshma.resumeanalyzer.entity.SkillMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillMasterRepository extends JpaRepository<SkillMaster, Long>{}

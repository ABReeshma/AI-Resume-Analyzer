package com.reeshma.resumeanalyzer.service;

import com.reeshma.resumeanalyzer.entity.Resume;
import com.reeshma.resumeanalyzer.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    public Resume saveResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    public List<Resume> getAllResume(){
        return resumeRepository.findAll();
    }
}
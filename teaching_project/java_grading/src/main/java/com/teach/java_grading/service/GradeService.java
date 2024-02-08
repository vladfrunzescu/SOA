package com.teach.java_grading.service;

import com.teach.java_grading.jpa.entity.Grade;
import com.teach.java_grading.jpa.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public List<Grade> findAllGradesByName(String name) {
        return gradeRepository.findAllByName(name);
    }

    public Integer calculateFinalGradeByName(String name) {
        return (int) Math.ceil(gradeRepository.findAllByName(name).stream()
                .mapToInt(Grade::getGrade)
                .average()
                .orElse(0.0));
    }

    public void saveGrade(Grade grade) {
        gradeRepository.save(grade);
    }
}

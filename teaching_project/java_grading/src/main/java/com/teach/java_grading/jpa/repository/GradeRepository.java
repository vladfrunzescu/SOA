package com.teach.java_grading.jpa.repository;

import com.teach.java_grading.jpa.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findAllByName(String name);
}

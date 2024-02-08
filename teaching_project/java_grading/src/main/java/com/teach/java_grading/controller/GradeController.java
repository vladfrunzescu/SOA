package com.teach.java_grading.controller;

import com.teach.java_grading.jpa.entity.Grade;
import com.teach.java_grading.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GradeController {

    @Autowired
    public GradeService gradeService;

    @GetMapping("/search")
    public ResponseEntity<List<Grade>> sendAllGradesByName(@RequestParam String name) {
        System.out.println("Search request received from website with name: " + name);
        List<Grade> gradeList = gradeService.findAllGradesByName(name);
        return ResponseEntity.ok(gradeList);
    }

    @GetMapping("/calculate")
    public ResponseEntity<Integer> sendFinalGradeByName(@RequestParam String name) {
        System.out.println("Calculate request received from website with name: " + name);
        Integer finalGrade = gradeService.calculateFinalGradeByName(name);
        return ResponseEntity.ok(finalGrade);
    }
}

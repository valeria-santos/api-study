package com.github.project.study.api.controllers;

import com.github.project.study.api.dtos.EnrollmentDto;
import com.github.project.study.api.entities.Enrollment;
import com.github.project.study.api.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/enrollment")
public class EnrollmentController {

    @Autowired
    EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<Enrollment> enrollInACourse(@RequestBody EnrollmentDto enrollmentDto){
        Enrollment enrollment = enrollmentService.enrollInACourse(enrollmentDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(enrollment);
    }
}

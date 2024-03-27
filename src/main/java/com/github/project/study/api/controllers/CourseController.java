package com.github.project.study.api.controllers;

import com.github.project.study.api.entities.Course;
import com.github.project.study.api.enums.Status;
import com.github.project.study.api.exceptions.EmptyListException;
import com.github.project.study.api.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody @Valid Course course) {
        Course courseCreated = courseService.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseCreated);
    }

    @PutMapping
    public ResponseEntity<Void> inactivateCourse(@RequestParam String code) {
        courseService.inactivateCourse(code);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<Page<Course>> listCoursesFilteredByStatus(@RequestParam Status status, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> courses = courseService.listCoursesFilteredByStatus(status, pageable);
        if (courses.isEmpty()) {
            throw new EmptyListException("No course registered yet");
        } else {
            return ResponseEntity.ok(courses);
        }
    }

}

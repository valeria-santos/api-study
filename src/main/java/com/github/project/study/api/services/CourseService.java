package com.github.project.study.api.services;

import com.github.project.study.api.entities.Course;
import com.github.project.study.api.entities.User;
import com.github.project.study.api.enums.Role;
import com.github.project.study.api.enums.Status;
import com.github.project.study.api.exceptions.CourseNotFoundException;
import com.github.project.study.api.exceptions.InstructorNotFoundException;
import com.github.project.study.api.repositories.CourseRepository;
import com.github.project.study.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Course save(Course course) {
        Optional<User> userFound = userRepository.findByName(course.getInstructor());
        if (userFound.isPresent() && userFound.get().getRole().equals(Role.INSTRUCTOR)) {
            course.setCreationDate(LocalDateTime.now());
            return courseRepository.save(course);
        }
        throw new InstructorNotFoundException("Instructor not found");
    }

    @Transactional
    public void inactivateCourse(String code) {
        Optional<Course> courseFound = courseRepository.findByCode(code);
        if (courseFound.isPresent()) {
            Course course = courseFound.get();
            course.setStatus(Status.INACTIVE);
            course.setInactivationDate(LocalDateTime.now());
            courseRepository.save(course);
        } else throw new CourseNotFoundException("Course not found. Please check if the code entered is correct");
    }

    public Page<Course> listCoursesFilteredByStatus(Status status, Pageable pageable) {
        return courseRepository.findByStatus(status, pageable);
    }

}

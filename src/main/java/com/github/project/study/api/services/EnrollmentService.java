package com.github.project.study.api.services;

import com.github.project.study.api.dtos.EnrollmentDto;
import com.github.project.study.api.entities.Course;
import com.github.project.study.api.entities.Enrollment;
import com.github.project.study.api.entities.User;
import com.github.project.study.api.enums.Status;
import com.github.project.study.api.exceptions.CourseNotFoundException;
import com.github.project.study.api.exceptions.EnrollmentAlreadyExistsException;
import com.github.project.study.api.exceptions.InactiveCourseException;
import com.github.project.study.api.exceptions.UserNotFoundException;
import com.github.project.study.api.repositories.CourseRepository;
import com.github.project.study.api.repositories.EnrollmentRepository;
import com.github.project.study.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Transactional
      public Enrollment enrollInACourse(EnrollmentDto enrollmentDto){
        Course course = courseRepository.findById(enrollmentDto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("The course was not found"));
        User user = userRepository.findById(enrollmentDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("The user was not found"));

        if (!course.getStatus().equals(Status.ACTIVE)) {
            throw new InactiveCourseException("This course is not available");
        }

        if (enrollmentRepository.existsByUserIdAndCourseId(enrollmentDto.getUserId(), enrollmentDto.getCourseId())){
            throw new EnrollmentAlreadyExistsException("The user is already enrolled in this course");
        }
            Enrollment enrollment = new Enrollment();
            enrollment.setUser(user);
            enrollment.setCourse(course);
            enrollment.setEnrollmentDate(LocalDateTime.now());
            return enrollmentRepository.save(enrollment);
    }
}

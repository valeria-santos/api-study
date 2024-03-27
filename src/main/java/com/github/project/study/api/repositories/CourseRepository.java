package com.github.project.study.api.repositories;

import com.github.project.study.api.entities.Course;
import com.github.project.study.api.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, BigInteger> {

    Optional<Course> findByCode(String code);

    Page<Course> findByStatus(Status status, Pageable pageable);
}

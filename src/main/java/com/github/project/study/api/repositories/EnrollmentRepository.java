package com.github.project.study.api.repositories;

import com.github.project.study.api.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface EnrollmentRepository extends JpaRepository<Enrollment, BigInteger> {
    boolean existsByUserIdAndCourseId(BigInteger userId, BigInteger courseId);

}

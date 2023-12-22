package com.hatrongtan99.app.repository.test;

import com.hatrongtan99.app.entity.test.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<StudentEntity, String> {
}

package com.hatrongtan99.app.repository.test;

import com.hatrongtan99.app.entity.test.GroupEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<GroupEntity, String> {
    Page<GroupEntity> findAll(Specification<GroupEntity> spec, Pageable pageable);
}

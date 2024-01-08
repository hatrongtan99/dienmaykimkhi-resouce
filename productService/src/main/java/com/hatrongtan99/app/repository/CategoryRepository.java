package com.hatrongtan99.app.repository;

import com.hatrongtan99.app.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsBySlug(String slug);

    Optional<CategoryEntity> findBySlug(String slug);

    Page<CategoryEntity> findByParentIdIsNull(Pageable pageable);

    @Query(
            value = """
                    with recursive cte as (
                    	SELECT c0.* from category as c0 where c0.parent_id = :parentId
                    	UNION all
                    	select c1.* from category as c1 INNER JOIN cte on c1.parent_id = cte.id
                    ) SELECT * from cte;
                    """,
            nativeQuery = true,
            countQuery = """
                    select count(*) from (
                    with RECURSIVE cte as (
                    	select c0.* from category as c0 where c0.parent_id = 1
                    	UNION all
                    	select c1.* from category as c1 INNER JOIN cte on c1.parent_id = cte.id
                    ) SELECT * from cte) as c;
                    """)
    Page<CategoryEntity> findAllChildByParentId(@Param("parentId") Long id, Pageable pageable);
}

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
                    	select c0.* from category as c0 where c0.parent_id = :parentId
                    	UNION all
                    	select c1.* from category as c1 INNER JOIN cte on c1.parent_id = cte.id
                    ) SELECT * from cte) as c;
                    """)
    Page<CategoryEntity> findAllChildByParentId(@Param("parentId") Long id, Pageable pageable);

    @Query(
            value = """
                    with recursive cte as (
                    	SELECT c0.* from category as c0 where c0.slug = :parentSlug
                    	UNION all
                    	select c1.* from category as c1 INNER JOIN cte on c1.parent_id = cte.id
                    ) SELECT * from cte where cte.slug != :parentSlug
                    """,
            nativeQuery = true,
            countQuery = """
                    select count(*) from (
                    with RECURSIVE cte as (
                    	select c0.* from category as c0 where c0.slug = :parentSlug
                    	UNION all
                    	select c1.* from category as c1 INNER JOIN cte on c1.parent_id = cte.id
                    ) SELECT * from cte where cte.slug != :parentSlug) as c;
                    """)
    Page<CategoryEntity> findAllChildByParentSlug(@Param("parentSlug") String slug, Pageable pageable);

    @Query(
            value = """
                     FROM CategoryEntity AS c0 INNER JOIN c0.parentId AS p WHERE p.slug = :parentSlug 
                    """
    )
    Page<CategoryEntity> findChildByParentSlug(@Param("parentSlug") String slug, Pageable pageable);
}

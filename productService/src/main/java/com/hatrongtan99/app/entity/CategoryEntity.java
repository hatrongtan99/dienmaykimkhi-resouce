package com.hatrongtan99.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "text default null")
    private String description;

    private String slug;

    private Long thumbnailId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private CategoryEntity parentId;

    @OneToMany(mappedBy = "parentId", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<CategoryEntity> categories = new ArrayList<>();

    @ManyToMany(mappedBy = "categories")
    private List<ProductEntity> products = new ArrayList<>();

    private boolean isActive;

    private int displayOrder;

}

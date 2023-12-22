package com.hatrongtan99.app.entity;

import com.hatrongtan99.app.entity.filter.FilterGroup;
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

    @Column(unique = true, nullable = false)
    private String slug;

    private Long thumbnailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CategoryEntity parentId;

    @OneToMany(mappedBy = "parentId", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<CategoryEntity> categories = new ArrayList<>();

    @ManyToMany(mappedBy = "categories")
    private List<ProductEntity> products = new ArrayList<>();

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<FilterGroup> filterGroups = new ArrayList<>();

    @Builder.Default
    private boolean isActive = true;

    private int displayOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof CategoryEntity)) {
            return false;
        }
        return (id != null) && id.equals(((CategoryEntity) o).id);
    }
}

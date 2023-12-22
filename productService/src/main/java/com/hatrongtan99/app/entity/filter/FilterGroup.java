package com.hatrongtan99.app.entity.filter;

import com.hatrongtan99.app.entity.CategoryEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "filter_group")
public class FilterGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "filterGroups", cascade = {CascadeType.PERSIST}, orphanRemoval = true)
    private Set<ProductFilter> filters = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "filter_group_categories",
            joinColumns = @JoinColumn(name = "filter_group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private List<CategoryEntity> categories = new ArrayList<>();

    @Builder.Default
    private boolean isActive = true;

    private int displayOrder;
}

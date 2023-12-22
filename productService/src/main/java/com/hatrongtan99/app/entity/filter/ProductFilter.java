package com.hatrongtan99.app.entity.filter;

import com.hatrongtan99.app.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "filter")
public class ProductFilter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "filter_group_id", nullable = false)
    private FilterGroup filterGroups;

    @ManyToMany
    @JoinTable(
            name = "product_filter",
            joinColumns = @JoinColumn(name = "filter_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private Set<ProductEntity> products = new HashSet<>();

    @Builder.Default
    private boolean isActive = true;

    private int displayOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductFilter)) {
            return false;
        }
        return id != null && id.equals(((ProductFilter) o).id);
    }
}

package com.hatrongtan99.app.entity;

import com.hatrongtan99.app.entity.filter.ProductFilter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity extends BaseAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "text default null")
    private String shortDescription;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "description_id")
    private DescriptionEntity descriptionId;

    private String sku;

    @Column(unique = true, nullable = false)
    private String slug;

    private String guarantee;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST}, mappedBy = "productId")
    @OrderBy("price")
    private List<PriceEntity> price = new ArrayList<>();

    private Long thumbnailId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "brand_id")
    private BrandEntity brandId;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private List<CategoryEntity> categories = new ArrayList<>();

    @OneToMany(mappedBy = "productId", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<ProductImageEntity> images = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "product_relate",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "relate_id", referencedColumnName = "id")
    )
    private List<ProductEntity> productRelate = new ArrayList<>();

    @OneToMany(mappedBy = "productId", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<ProductMetaDataEntity> metadata = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
    private Set<ProductFilter> filters = new HashSet<>();

    private boolean isAvailInStock;

    @Builder.Default
    private boolean isActive = true;

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof ProductEntity)) {
            return false;
        }
        return id != null && id.equals(((ProductEntity) o).id);
    }
}

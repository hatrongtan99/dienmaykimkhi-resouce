package com.hatrongtan99.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "description_id")
    private DescriptionEntity descriptionId;

    private String sku;

    private String slug;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST}, mappedBy = "productId")
    private List<PriceEntity> priceId = new ArrayList<>();

    private Long thumbnailId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "brand_id")
    private BrandEntity brandId;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private List<CategoryEntity> categories = new ArrayList<>();

    @OneToMany(mappedBy = "productId", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private List<ProductImageEntity> images = new ArrayList<>();

    private boolean isAvailInStock;

    private boolean isActive;
}

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
@Table(name = "brand")
public class BrandEntity extends BaseAuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long thumbnailId;

    @Column(unique = true, nullable = false)
    private String slug;

    @OneToMany(mappedBy = "brandId")
    private List<ProductEntity> products = new ArrayList<>();

    @Builder.Default
    private boolean isActive = true;

    private int displayOrder;

}

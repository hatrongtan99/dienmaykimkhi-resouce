package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.entity.PriceEntity;
import com.hatrongtan99.app.entity.ProductEntity;
import com.hatrongtan99.app.repository.ProductRepository;
import com.hatrongtan99.app.services.IProductReadService;
import jakarta.persistence.criteria.*;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/bff-admin/products", "/bff-customer/products"})
@RequiredArgsConstructor
public class ProductReadController {

    private final IProductReadService productReadService;
    private final ProductRepository productRepository;

    @GetMapping({"/category-slug/{categorySlug}"})
    public ResponseEntity<?> getProductWithCategorySlug(
            @PathVariable("categorySlug") String categorySlug,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageLimit", required = false, defaultValue = "5") int pageLimit,
            @RequestParam Map<String, String> allRequestParams
    ) {
        return ResponseEntity.ok(this.productReadService.listProductWithPageBySlugCategory(pageNumber, pageLimit, categorySlug, allRequestParams));
    }

    @GetMapping({"/brand-slug/{brand}"})
    public ResponseEntity<?> getProductWithBrandSlug(
            @PathVariable("brand") String brandSlug,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageLimit", required = false, defaultValue = "5") int pageLimit,
            @RequestParam Map<String, String> allRequestParams
    ) {

        return ResponseEntity.ok(this.productReadService.listProductWithPageBySlugBrand(pageNumber, pageLimit, brandSlug, allRequestParams));
    }



    @GetMapping
    public Object test() {
//        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
//        CriteriaQuery<Tuple> criteriaQuery = cb.createQuery(Tuple.class);
//        Root<ProductEntity> root = criteriaQuery.from(ProductEntity.class);
//        List<Predicate> predicates = new ArrayList<>();
//        Join<ProductEntity, PriceEntity> productPriceJoin = root.join("price");
////        predicates.add(cb.equal(productPriceJoin.get("isActive"), true));
//        criteriaQuery.multiselect(root.get("id"), productPriceJoin.get("price").as(Double.class));
//        var products = this.entityManager.createQuery(criteriaQuery.where(predicates.toArray(new Predicate[0]))).getResultList();
//
//

        Specification<ProductEntity> specification = Specification.where((root, query, criteriaBuilder) -> {
            Join<ProductEntity, PriceEntity> join = root.join("price");
            query.multiselect(root.get("id"), join.get("price"));
            return criteriaBuilder.equal(join.get("isActive"), true);
        });
        specification = specification.and((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("id"), 1);
        });
        List<ProductEntity> productEntities = this.productRepository.findAll(specification);
        productEntities.stream().map(p -> p.getId()).forEach(id -> System.out.println(id));


//        Specification<PriceEntity> specification = Specification.where((root, query, criteriaBuilder) -> {
//
//            return criteriaBuilder.equal(root.get("isActive"), true);
//        });
//        List<PriceEntity> productEntities = this.priceRepository.findAll(specification);
//        var products = productEntities.stream().map(p -> new Product(p.getId(), p.getPrice())).toList();
        return "success";
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static class Product {
        private Long id;
        private Double price;
    }
}


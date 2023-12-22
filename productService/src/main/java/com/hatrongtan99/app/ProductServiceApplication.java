package com.hatrongtan99.app;

import com.hatrongtan99.app.entity.ProductAttributeEntity;
import com.hatrongtan99.app.repository.ProductAttributeRepository;
import com.hatrongtan99.app.utils.Constant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(
            ProductAttributeRepository attributeRepository
    ) {
        return (args) -> {
            // init catalog
            String catalogAttributeName = Constant.CATALOG_ATTRIBUTE;
            Optional<ProductAttributeEntity> catalogAttribute = attributeRepository.findByName(catalogAttributeName);
            if (catalogAttribute.isEmpty()) {
                ProductAttributeEntity catalogAttributeInit = ProductAttributeEntity.builder().name(catalogAttributeName).build();
                attributeRepository.save(catalogAttributeInit);
            }
        };
    }

}

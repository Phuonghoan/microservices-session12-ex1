package org.example.productservice.service;

import lombok.RequiredArgsConstructor;
import org.example.productservice.dto.ProductRequestDTO;
import org.example.productservice.dto.ProductResponseDTO;
import org.example.productservice.entity.Product;
import org.example.productservice.exception.ResourceNotFoundException;
import org.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository
            productRepository;

    // =========================================
    // CREATE PRODUCT
    // =========================================

    @Transactional
    public ProductResponseDTO createProduct(
            ProductRequestDTO request
    ) {

        Product product =
                Product
                        .builder()

                        .name(
                                request.name()
                        )

                        .price(
                                request.price()
                        )

                        .stockQuantity(
                                request.stockQuantity()
                        )

                        .build();


        Product saved =
                productRepository
                        .save(product);


        return mapToResponse(saved);
    }

    // =========================================
    // GET PRODUCT BY ID
    // =========================================

    @Transactional(readOnly = true)
    public ProductResponseDTO getProductById(
            Long id
    ) {

        Product product =
                productRepository
                        .findById(id)

                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Sản phẩm với ID "
                                                        + id
                                                        + " không tồn tại!"
                                        )
                        );


        return mapToResponse(product);
    }

    // =========================================
    // GET ALL PRODUCTS
    // =========================================

    @Transactional(readOnly = true)
    public List<ProductResponseDTO>
    getAllProducts() {

        return productRepository
                .findAll()

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    // =========================================
    // ENTITY -> DTO
    // =========================================

    private ProductResponseDTO mapToResponse(
            Product product
    ) {

        return new ProductResponseDTO(

                product.getId(),

                product.getName(),

                product.getPrice(),

                product.getStockQuantity()
        );
    }
}

package org.example.productservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.productservice.dto.ProductRequestDTO;
import org.example.productservice.dto.ProductResponseDTO;
import org.example.productservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService
            productService;

    // =========================================
    // POST /api/v1/products
    // =========================================

    @PostMapping
    public ResponseEntity<ProductResponseDTO>
    createProduct(

            @Valid
            @RequestBody
            ProductRequestDTO request
    ) {

        return ResponseEntity
                .status(201)
                .body(
                        productService
                                .createProduct(request)
                );
    }

    // =========================================
    // GET /api/v1/products/{id}
    // =========================================

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO>
    getProductById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(

                productService
                        .getProductById(id)
        );
    }

    // =========================================
    // GET /api/v1/products
    // =========================================

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>>
    getAllProducts() {

        return ResponseEntity.ok(

                productService
                        .getAllProducts()
        );
    }
}

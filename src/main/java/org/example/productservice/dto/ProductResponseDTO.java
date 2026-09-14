package org.example.productservice.dto;

import java.math.BigDecimal;

public record ProductResponseDTO(

        Long id,

        String name,

        BigDecimal price,

        Integer stockQuantity

) {
}

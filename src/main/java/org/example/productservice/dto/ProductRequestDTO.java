package org.example.productservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProductRequestDTO(

        @NotBlank(
                message = "Tên sản phẩm không được để trống"
        )
        String name,

        @NotNull(
                message = "Giá sản phẩm không được để trống"
        )
        @Min(
                value = 1,
                message = "Giá sản phẩm phải lớn hơn 0"
        )
        BigDecimal price,

        @NotNull(
                message = "Số lượng tồn kho không được để trống"
        )
        @Min(
                value = 0,
                message = "Số lượng tồn kho không được âm"
        )
        Integer stockQuantity
) {
}

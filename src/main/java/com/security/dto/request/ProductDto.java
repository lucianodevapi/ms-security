package com.security.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @NotBlank
    private String name;

    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @Min(value = 1)
    private Long categoryId;
}

package com.ecommerce.products.request;

import com.ecommerce.products.models.Category;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotBlank
    @NotNull
    @Size(min = 3, max = 100)
    String name;
    String description;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    BigDecimal price;
    @Min(value = 0)
    Integer stock;
    @NotNull
    @Valid
    Category category;
}

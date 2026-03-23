package com.apiproductcrud.assignmentapidibimbing.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    @Size(min = 3, max = 100, message = "Product name must be between 3 and 100 character")
    private String name;

    @Size(max = 500, message = "Description cann't exceed 500 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @DecimalMax(value = "999999999.99", message = "Price cannot exceed 999,999,999.99")
    private BigDecimal price;

    @NotNull(message = "Price is required")
    @Min(value = 0,  message = "stock quantity cannot be negative")
    @Max(value = 999999, message = "Price cannot exceed 999,999")
    private Integer stockQuantity;

    @Size(max = 50, message = "Category cannot exceed 50 characters")
    private String category;

    private Boolean isActive = true;
}

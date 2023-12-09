package com.example.orderservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateProductDTO {

    @NotBlank
    private String name;
    private String desc;

    private String category;
    @NotBlank
    @Min(value = 0)
    private BigDecimal price;
}

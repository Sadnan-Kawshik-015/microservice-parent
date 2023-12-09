package com.example.orderservice.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateCategoryResponseDTO {
    private String id;
    private String name;
}

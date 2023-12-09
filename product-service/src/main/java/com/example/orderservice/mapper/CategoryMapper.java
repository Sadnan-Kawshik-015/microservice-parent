package com.example.orderservice.mapper;

import com.example.orderservice.dto.CreateCategoryDTO;
import com.example.orderservice.dto.CreateCategoryResponseDTO;
import com.example.orderservice.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category dtoToModel(CreateCategoryDTO createCategoryDTO);
    CreateCategoryResponseDTO modelToDTO(Category category);
}

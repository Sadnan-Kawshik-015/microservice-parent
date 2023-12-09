package com.example.orderservice.mapper;

import com.example.orderservice.dto.CreateProductDTO;
import com.example.orderservice.dto.CreateProductResponseDTO;
import com.example.orderservice.dto.ProductResponseDTO;
import com.example.orderservice.model.Category;
import com.example.orderservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper( componentModel = "spring",imports = {UUID.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    CreateProductResponseDTO productCreationResponseMapper(Product product);

    @Mapping(source = "productDTO.desc",target = "description")
    @Mapping(target = "category", ignore = true)
    Product dtoToModel(CreateProductDTO productDTO);




    @Mapping(source = "c.name",target = "category")
    @Mapping(source = "p.id",target = "id")
    @Mapping(source = "p.name",target = "name")
    @Mapping(source = "p.price",target = "price")
    ProductResponseDTO productToProductResponse(Product p,Category c);


}

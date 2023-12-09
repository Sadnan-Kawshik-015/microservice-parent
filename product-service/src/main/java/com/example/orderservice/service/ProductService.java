package com.example.orderservice.service;

import com.example.orderservice.dto.CreateProductDTO;
import com.example.orderservice.dto.CreateProductResponseDTO;
import com.example.orderservice.dto.ProductResponseDTO;
import com.example.orderservice.mapper.ProductMapper;
import com.example.orderservice.model.Category;
import com.example.orderservice.model.Product;
import com.example.orderservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    public CreateProductResponseDTO createProduct(CreateProductDTO createProductDTO) throws Exception {
        try {
            Category category = categoryRepository.findById(createProductDTO.getCategory())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            Product product = productMapper.dtoToModel(createProductDTO);
            product.setCategory(category);

            return productMapper.productCreationResponseMapper(product);
        } catch (Exception e) {
            throw e;
        }
    }
    public List<ProductResponseDTO> getAllProducts() throws Exception {
        try {
            return productRepository
                    .fetchAllProduct()
                    .stream()
                    .map(e->productMapper.productToProductResponse(e,e.getCategory()))
                    .toList();

        } catch (Exception e) {
            throw e;
        }
    }
    public ProductResponseDTO getProductById(String id) throws Exception {
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("product not found"));
            return productMapper.productToProductResponse(product,product.getCategory());

        } catch (Exception e) {
            throw e;
        }
    }
    public List<ProductResponseDTO> getProductsCategoryById(String id) throws Exception {
        try {
            return productRepository.fetchProductByCategoryId(id).stream().map(e->productMapper.productToProductResponse(e,e.getCategory())).toList();

        } catch (Exception e) {
            throw e;
        }
    }
}

package com.example.orderservice.service;

import com.example.orderservice.dto.*;
import com.example.orderservice.mapper.CategoryMapper;
import com.example.orderservice.model.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    public CreateCategoryResponseDTO createCategory(CreateCategoryDTO createCategoryDTO) throws Exception {
        try {

            Category category = categoryMapper.dtoToModel(createCategoryDTO);


            return categoryMapper.modelToDTO(category);
        } catch (Exception e) {
            throw e;
        }
    }
    public List<CreateCategoryResponseDTO> getAllCategory() throws Exception {
        try {
            return categoryRepository
                    .findAll()
                    .stream()
                    .map(e->categoryMapper.modelToDTO(e))
                    .toList();

        } catch (Exception e) {
            throw e;
        }
    }
    public CreateCategoryResponseDTO getCategoryById(String id) throws Exception {
        try {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("category not found"));
            return categoryMapper.modelToDTO(category);

        } catch (Exception e) {
            throw e;
        }
    }

}

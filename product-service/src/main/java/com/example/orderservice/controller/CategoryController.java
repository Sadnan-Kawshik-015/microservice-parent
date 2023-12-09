package com.example.orderservice.controller;

import com.example.orderservice.dto.*;
import com.example.orderservice.service.CategoryService;
import com.example.orderservice.util.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/category", produces = "application/json;charset=UTF-8")
@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@RequiredArgsConstructor
public class CategoryController extends BaseController{
    private final CategoryService categoryService;
    @PostMapping
    public ResponseEntity<ResponseModelDTO> createCategory(@RequestBody CreateCategoryDTO createCategoryDTO) {
        ResponseModelDTO responseModelDTO = new ResponseModelDTO();
        try {
            CreateCategoryResponseDTO createProductResponseDTO = categoryService.createCategory(createCategoryDTO);
            return ResponseEntity.ok(ResponseModelDTO.builder()
                    .status(Status.success.name())
                    .message("category created successfully")
                    .data(createProductResponseDTO)
                    .build());

        } catch (Exception e) {
            return exceptionTask(e);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseModelDTO> getAllCategories() {
        try {
            List<CreateCategoryResponseDTO> dtos = categoryService.getAllCategory();
            return ResponseEntity.ok(
                    ResponseModelDTO.builder()
                            .status(Status.success.toString())
                            .message((dtos
                                    .isEmpty()) ? "no categories found!" : "categories found!")
                            .data(dtos)
                            .build()
            );

        } catch (Exception e) {
            return exceptionTask(e);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseModelDTO> getCategoryById( @PathVariable String id) {
        try {
            CreateCategoryResponseDTO dto = categoryService.getCategoryById(id);
            return ResponseEntity.ok(
                    ResponseModelDTO.builder()
                            .status(Status.success.toString())
                            .message("category found!")
                            .data(dto)
                            .build()
            );

        } catch (Exception e) {
            return exceptionTask(e);
        }

    }

}

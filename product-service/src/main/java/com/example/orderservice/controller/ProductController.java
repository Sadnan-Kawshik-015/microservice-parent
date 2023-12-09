package com.example.orderservice.controller;

import com.example.orderservice.dto.CreateProductResponseDTO;
import com.example.orderservice.dto.ProductResponseDTO;
import com.example.orderservice.dto.CreateProductDTO;
import com.example.orderservice.dto.ResponseModelDTO;
import com.example.orderservice.service.ProductService;
import com.example.orderservice.util.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product", produces = "application/json;charset=UTF-8")
@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@RequiredArgsConstructor
public class ProductController extends BaseController{
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseModelDTO> createProduct(@RequestBody CreateProductDTO createProductDTO) {
        ResponseModelDTO responseModelDTO = new ResponseModelDTO();
        try {
            CreateProductResponseDTO createProductResponseDTO = productService.createProduct(createProductDTO);
            return ResponseEntity.ok(ResponseModelDTO.builder()
                    .status(Status.success.name())
                    .message("product created successfully")
                    .data(createProductResponseDTO)
                    .build());

        } catch (Exception e) {
            return exceptionTask(e);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseModelDTO> getAllProducts() {
        try {
            List<ProductResponseDTO> dtos = productService.getAllProducts();
            return ResponseEntity.ok(
                    ResponseModelDTO.builder()
                            .status(Status.success.toString())
                            .message((dtos
                                    .isEmpty()) ? "no product found!" : "projects found!")
                            .data(dtos)
                            .build()
            );

        } catch (Exception e) {
            return exceptionTask(e);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseModelDTO> getProductById( @PathVariable String id) {
        try {
            ProductResponseDTO dto = productService.getProductById(id);
            return ResponseEntity.ok(
                    ResponseModelDTO.builder()
                            .status(Status.success.toString())
                            .message("product found!")
                            .data(dto)
                            .build()
            );

        } catch (Exception e) {
            return exceptionTask(e);
        }

    }
    @GetMapping("/{category_id}")
    public ResponseEntity<ResponseModelDTO> getProductByCategoryId( @PathVariable("category_id") String categoryId) {
        try {
            List<ProductResponseDTO> dtos = productService.getProductsCategoryById(categoryId);
            return ResponseEntity.ok(
                    ResponseModelDTO.builder()
                            .status(Status.success.toString())
                            .message((dtos
                                    .isEmpty()) ? "no product found!" : "products found!")
                            .data(dtos)
                            .build()
            );

        } catch (Exception e) {
            return exceptionTask(e);
        }

    }
}

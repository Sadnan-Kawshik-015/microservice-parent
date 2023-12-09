package com.example.orderservice.controller;

import com.example.orderservice.dto.InventoryResponseDTO;
import com.example.orderservice.dto.OrderRequestDTO;
import com.example.orderservice.dto.ResponseModelDTO;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.util.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order", produces = "application/json;charset=UTF-8")
@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@RequiredArgsConstructor
public class OrderController extends BaseController{
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ResponseModelDTO> placeOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        ResponseModelDTO responseModelDTO = new ResponseModelDTO();
        try {
            orderService.placeOrder(orderRequestDTO);
            return ResponseEntity.ok(ResponseModelDTO.builder()
                    .status(Status.success.name())
                    .message("order placed successfully")
                    .data(null)
                    .build());

        } catch (Exception e) {
            return exceptionTask(e);
        }
    }

//    @GetMapping
//    public ResponseEntity<ResponseModelDTO> getAllProducts() {
//        try {
//            List<InventoryResponseDTO> dtos = orderService.getAllProducts();
//            return ResponseEntity.ok(
//                    ResponseModelDTO.builder()
//                            .status(Status.success.toString())
//                            .message((dtos
//                                    .isEmpty()) ? "no product found!" : "projects found!")
//                            .data(dtos)
//                            .build()
//            );
//
//        } catch (Exception e) {
//            return exceptionTask(e);
//        }
//
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<ResponseModelDTO> getProductById( @PathVariable String id) {
//        try {
//            InventoryResponseDTO dto = orderService.getProductById(id);
//            return ResponseEntity.ok(
//                    ResponseModelDTO.builder()
//                            .status(Status.success.toString())
//                            .message("product found!")
//                            .data(dto)
//                            .build()
//            );
//
//        } catch (Exception e) {
//            return exceptionTask(e);
//        }
//
//    }
//    @GetMapping("/{category_id}")
//    public ResponseEntity<ResponseModelDTO> getProductByCategoryId( @PathVariable("category_id") String categoryId) {
//        try {
//            List<InventoryResponseDTO> dtos = orderService.getProductsCategoryById(categoryId);
//            return ResponseEntity.ok(
//                    ResponseModelDTO.builder()
//                            .status(Status.success.toString())
//                            .message((dtos
//                                    .isEmpty()) ? "no product found!" : "products found!")
//                            .data(dtos)
//                            .build()
//            );
//
//        } catch (Exception e) {
//            return exceptionTask(e);
//        }
//
//    }
}

package com.example.orderservice.controller;

import com.example.orderservice.dto.ResponseModelDTO;
import com.example.orderservice.util.Status;
import com.example.orderservice.util.exceptions.RequiredResourceNotFoundException;
import com.example.orderservice.util.exceptions.ResourceNotFoundException;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {
    @SneakyThrows
    protected ResponseEntity<ResponseModelDTO> exceptionTask(Exception e) {
        e = parseExceptions(e);
        ResponseModelDTO responseModelDTO = new ResponseModelDTO();
        String message;
        if (e instanceof ResourceNotFoundException) {
            message = e.getMessage();
            responseModelDTO.setStatus(Status.success.name());
            responseModelDTO.setMessage(message);
            return ResponseEntity.ok(responseModelDTO);
        } else if (e instanceof RequiredResourceNotFoundException) {
            message = e.getMessage();
            responseModelDTO.setStatus(Status.error.name());
        } else {
            responseModelDTO.setStatus(Status.error.name());
            message = e.getMessage();
        }
        responseModelDTO.setMessage(message);
        return ResponseEntity.badRequest()
                .body(responseModelDTO);
    }

    public <T extends Throwable> Exception parseExceptions(T t) {
        Exception cause = (Exception) t.getCause();
        if (cause == null) {
            return (Exception) t;
        } else {
            parseExceptions(t.getCause());
        }
        return cause;
    }
}

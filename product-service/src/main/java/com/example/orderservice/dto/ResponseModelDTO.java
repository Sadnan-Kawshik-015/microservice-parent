package com.example.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseModelDTO {
    private String status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String correlation_id;
    private Object data;
}

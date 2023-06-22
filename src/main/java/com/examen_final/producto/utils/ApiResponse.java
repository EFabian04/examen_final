package com.examen_final.producto.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
public class ApiResponse {
    private String message;
    private Object data;
}

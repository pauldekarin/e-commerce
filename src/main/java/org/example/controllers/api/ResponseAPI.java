package org.example.controllers.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class ResponseAPI<T> {
    private Integer status;
    private String uri;
    private String message;
    private T data;
}

package com.ropulva.tms.exception;

import lombok.*;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String error;
    private String message;
    private Integer status;
}
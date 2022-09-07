package com.example.elections.rest.exception;

import lombok.Data;

@Data
public class ExceptionResponse {

    private String errorMessage;

    private String path;

    private Integer status;

    private String timestamp;

}

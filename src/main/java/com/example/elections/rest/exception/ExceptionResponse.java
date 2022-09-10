package com.example.elections.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private String errorMessage;

    private String path;

    private Integer status;

    private String timestamp;

}

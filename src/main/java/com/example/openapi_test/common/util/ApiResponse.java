package com.example.openapi_test.common.util;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Builder
public record ApiResponse(int code, String message, Map<String, Object> result) {
    public static String RESULT_MSG = "ok";
    public static int RESULT_OK_VALUE = HttpStatus.OK.value();
}

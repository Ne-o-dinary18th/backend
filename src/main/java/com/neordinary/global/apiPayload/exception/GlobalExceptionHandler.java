package com.neordinary.global.apiPayload.exception;


import com.neordinary.global.apiPayload.ApiResponse;
import com.neordinary.global.apiPayload.code.status.ErrorStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralException(GeneralException e) {
        ErrorStatus status = e.getErrorStatus();

        HttpStatus httpStatus = switch (status) {
            case REPORT_NOT_FOUND,
                    TAG_NOT_FOUND,
                    RECEIPT_NOT_FOUND -> HttpStatus.NOT_FOUND;

            case RECEIPT_NOT_IN_TAG,
                    RECEIPT_INVALID_INPUT,
                    TAG_NAME_REQUIRED,
                    TAG_NAME_ALREADY_EXISTS -> HttpStatus.BAD_REQUEST;

            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };

        return ResponseEntity
                .status(httpStatus)
                .body(ApiResponse.onFailure(status.getCode(), status.getMessage(), null));
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<Object>> handleNoSuchElement(NoSuchElementException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.onFailure("404", e.getMessage(), null));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadRequest(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.onFailure("400", e.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception e) {
        log.error("[500] Unexpected Error", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.onFailure("500", "서버 내부 오류가 발생했습니다.", null));
    }
}
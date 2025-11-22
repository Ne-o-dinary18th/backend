package com.neordinary.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ErrorReasonDTO {
    private String code;
    private String message;
    private HttpStatus httpStatus;

    public ErrorReasonDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
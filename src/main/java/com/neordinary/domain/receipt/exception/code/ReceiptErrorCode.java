package com.neordinary.domain.receipt.exception.code;

import com.neordinary.global.apiPayload.code.BaseErrorCode;
import com.neordinary.global.apiPayload.code.ErrorReasonDTO;
import com.neordinary.global.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReceiptErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, ReasonDTO.builder().build());

    private final HttpStatus reasonHttpStatus;
    private final ReasonDTO reason;
}

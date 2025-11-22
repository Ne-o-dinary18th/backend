package com.neordinary.global.apiPayload.code.status;

import com.neordinary.global.apiPayload.code.BaseErrorCode;
import com.neordinary.global.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    REPORT_NOT_FOUND(HttpStatus.NOT_FOUND, "4041", "보고서를 찾을 수 없습니다."),
    TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "4042", "태그를 찾을 수 없습니다."),
    RECEIPT_NOT_FOUND(HttpStatus.NOT_FOUND, "4043", "영수증을 찾을 수 없습니다."),

    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "5000", "서버 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return new ErrorReasonDTO(code, message);
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return new ErrorReasonDTO(code, message, httpStatus);
    }
}

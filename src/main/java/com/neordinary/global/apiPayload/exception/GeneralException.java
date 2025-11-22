package com.neordinary.global.apiPayload.exception;


import com.neordinary.global.apiPayload.code.status.ErrorStatus;

public class GeneralException extends RuntimeException {

    private final ErrorStatus errorStatus;

    public GeneralException(ErrorStatus errorStatus) {
        super(errorStatus.getMessage()); // 부모 RuntimeException에 메시지 세팅
        this.errorStatus = errorStatus;
    }

    public ErrorStatus getErrorStatus() {
        return errorStatus;
    }

    public String getErrorCode() {
        return errorStatus.getCode();
    }

    @Override
    public String getMessage() {
        return errorStatus.getMessage();
    }
}
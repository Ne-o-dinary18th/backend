package com.neordinary.global.apiPayload.code.status;

import com.neordinary.global.apiPayload.code.BaseErrorCode;
import com.neordinary.global.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    REPORT_NOT_FOUND("REPORT4041", "보고서를 찾을 수 없습니다."),

    RECEIPT_NOT_IN_TAG("RECEIPT4002", "해당 태그에 속하지 않은 영수증이 포함되어 있습니다."),
    RECEIPT_NOT_FOUND("RECEIPT4043", "영수증을 찾을 수 없습니다."),
    RECEIPT_INVALID_INPUT("RECEIPT4002", "영수증 입력값이 올바르지 않습니다."),
    RECEIPT_CANNOT_DELETE_IN_REPORT("RECEIPT4003", "해당 영수증을 사용하는 보고서가 있습니다."),

    INTERNAL_ERROR("5000", "서버 오류가 발생했습니다."),

    TAG_CREATE_ERROR("TAG4001", "태그 생성에 실패했습니다."),
    TAG_NOT_FOUND("TAG4002", "해당 태그가 없습니다."),
    TAG_NAME_REQUIRED("TAG4003", "태그명은 필수 입니다."),
    TAG_NAME_ALREADY_EXISTS("TAG4004", "해당 태그명은 이미 존재합니다."),
    TAG_CANT_DELETE("TAG4005", "해당 태그를 삭제할 수 없습니다.");

    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return new ErrorReasonDTO(code, message);
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return new ErrorReasonDTO(code, message);
    }
}

package com.neordinary.global.apiPayload.code.status;

import org.springframework.http.HttpStatus;

public enum ErrorStatus {
    USER_NOT_FOUND("U001","해당 유저를 찾을 수 없습니다."),

    USER_ALREADY_EXISTS("U002", "이미 존재하는 유저입니다."),
    INVALID_INPUT("C001", "입력 값이 유효하지 않습니다."),
    INTERNAL_ERROR("S001", "서버 내부 오류가 발생했습니다."),
    ALREADY_REGISTERED_USER("USER409", "이미 일반 회원가입을 완료한 사용자입니다."),

    TAG_CREATE_ERROR("TAG4001", "태그 생성에 실패했습니다."),
    TAG_NOT_FOUND("TAG4002", "해당 태그가 없습니다."),
    TAG_NAME_REQUIRED("TAG4003", "태그명은 필수 입니다."),
    TAG_NAME_ALREADY_EXISTS("TAG4004", "해당 태그명은 이미 존재합니다."),

    RECEIPT_NOT_FOUND("RECEIPT400", "해당 영수증이 없습니다.");


    private final String code;
    private final String message;

    ErrorStatus(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return code;
    }
    public String getMessage(){
        return message;
    }


}

package com.neordinary.domain.report.dto;

import lombok.Getter;

@Getter
public class OcrExpenseRequest {

    private Long tagId;
    private String ocrText;
    private String imageUrl;
}

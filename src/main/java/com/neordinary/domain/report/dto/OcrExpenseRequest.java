package com.neordinary.domain.report.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class OcrExpenseRequest {

    @Schema(description = "태그 ID", example = "12")
    private Long tagId;

    @Schema(description = "OCR 원문 텍스트", example = "스타벅스 2025-11-20 총금액 5,100원 ...")
    private String ocrText;
}

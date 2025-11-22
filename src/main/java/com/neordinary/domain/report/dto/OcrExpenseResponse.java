package com.neordinary.domain.report.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "지출내역 파싱 결과")
public class OcrExpenseResponse {

    @Schema(description = "지출내역 ID")
    private Long expenseId;

    @Schema(description = "상호", example = "스타벅스 강남역점")
    private String storeName;

    @Schema(description = "날짜", example = "2025-11-20")
    private String date;

    @Schema(description = "총 금액", example = "5100")
    private Integer totalPrice;
}

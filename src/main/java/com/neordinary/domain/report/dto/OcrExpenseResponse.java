package com.neordinary.domain.report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OcrExpenseResponse {

    private Long expenseId;
    private String storeName;
    private String date;
    private Integer totalPrice;
}

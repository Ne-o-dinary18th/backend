package com.neordinary.domain.report.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportReceiptResponse {
    private Long receiptId;
    private Long amount;
    private LocalDate date;
    private String storeName;
}


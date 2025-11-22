package com.neordinary.domain.report.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportTagFilterResponse {
    private Long totalAmount;
    private String tagName;
    private Long reportId;
    private LocalDate reportDate;
}

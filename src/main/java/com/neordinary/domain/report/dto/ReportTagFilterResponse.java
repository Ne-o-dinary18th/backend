package com.neordinary.domain.report.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportTagFilterResponse {
    private Long totalAmount;
    private String tagName;
    private Long reportId;
}

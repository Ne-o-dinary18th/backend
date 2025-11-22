package com.neordinary.domain.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportSummaryResponse {

    private Long reportId;
    private Long tagId;
    private String tagName;
    private LocalDateTime createdAt;
}
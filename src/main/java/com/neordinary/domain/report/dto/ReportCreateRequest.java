package com.neordinary.domain.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportCreateRequest { // 보고서 생성

    private Long tagId;
    private List<Long> receiptIds;
}


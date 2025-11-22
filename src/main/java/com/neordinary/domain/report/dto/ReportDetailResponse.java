package com.neordinary.domain.report.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportDetailResponse {

    private Long reportId;

    // 상단 정보
    private String tagName;
    private LocalDate reportDate;  // 생성일
    private int memberCount;
    private List<String> members;
    private String managerName;
    private String managerAccount;

    // 지출 리스트
    private List<ReportReceiptResponse> receipts;

    // 총 지출 금액
    private Long totalAmount;
}

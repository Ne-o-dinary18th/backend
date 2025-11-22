package com.neordinary.domain.report.controller;

import com.neordinary.domain.report.dto.ReportCreateRequest;
import com.neordinary.domain.report.dto.ReportDetailResponse;
import com.neordinary.domain.report.dto.ReportTagFilterResponse;
import com.neordinary.domain.report.service.ReportService;
import com.neordinary.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
@Tag(name = "보고서", description = "보고서 생성 및 조회 API")
public class ReportController {

    private final ReportService reportService;

    // 보고서 생성
    @PostMapping
    @Operation(summary = "보고서 생성", description = "특정 태그 내 선택한 영수증들을 기반으로 보고서를 생성합니다.")
    public ResponseEntity<ApiResponse<Long>> createReport(@RequestBody ReportCreateRequest request) {
        Long reportId = reportService.createReport(request);
        return ResponseEntity.ok(ApiResponse.onSuccess(reportId));
    }

    // 보고서 전체 조회
    @GetMapping
    @Operation(summary = "전체 보고서 조회", description = "생성된 모든 보고서를 조회합니다.")
    public ResponseEntity<ApiResponse<List<ReportDetailResponse>>> getAllReports() {
        List<ReportDetailResponse> reports = reportService.getAllReports();
        return ResponseEntity.ok(ApiResponse.onSuccess(reports));
    }

    // 보고서 개별 조회
    @GetMapping("/{reportId}")
    @Operation(summary = "보고서 개별 조회", description = "특정 보고서의 상세 내용을 조회합니다.")
    public ResponseEntity<ApiResponse<ReportDetailResponse>> getReport(@PathVariable Long reportId) {
        ReportDetailResponse response = reportService.getReport(reportId);
        return ResponseEntity.ok(ApiResponse.onSuccess(response));
    }

    // 보고서 태그이름별 조회
    @GetMapping("/tag/{tagId}")
    @Operation(summary = "특정 태그에 속한 보고서 조회",
            description = "태그 ID에 해당하는 보고서를 필터링하여 반환합니다.")
    public ResponseEntity<ApiResponse<List<ReportTagFilterResponse>>> getReportsByTag(
            @PathVariable Long tagId
    ) {
        List<ReportTagFilterResponse> reports = reportService.getReportsByTagId(tagId);
        return ResponseEntity.ok(ApiResponse.onSuccess(reports));
    }

}
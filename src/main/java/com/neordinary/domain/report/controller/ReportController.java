package com.neordinary.domain.report.controller;

import com.neordinary.domain.report.dto.ReportCreateRequest;
import com.neordinary.domain.report.dto.ReportDetailResponse;
import com.neordinary.domain.report.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "보고서 생성 성공"),
            @ApiResponse(responseCode = "404", description = "태그 또는 영수증을 찾을 수 없음")
    })
    public ResponseEntity<Long> createReport(@RequestBody ReportCreateRequest request) {
        Long reportId = reportService.createReport(request);
        return ResponseEntity.ok(reportId);
    }

    // 보고서 전체 조회
    @GetMapping
    @Operation(summary = "전체 보고서 조회", description = "생성된 모든 보고서를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    public ResponseEntity<List<ReportDetailResponse>> getAllReports() {
        List<ReportDetailResponse> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    // 보고서 개별 조회
    @GetMapping("/{reportId}")
    @Operation(summary = "보고서 개별 조회", description = "특정 보고서의 상세 내용을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "보고서를 찾을 수 없음")
    })
    public ResponseEntity<ReportDetailResponse> getReport(@PathVariable Long reportId) {
        ReportDetailResponse response = reportService.getReport(reportId);
        return ResponseEntity.ok(response);
    }
}
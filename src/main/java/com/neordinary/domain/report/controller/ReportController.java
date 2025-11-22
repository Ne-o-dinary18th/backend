package com.neordinary.domain.report.controller;

import com.neordinary.domain.report.dto.ReportCreateRequest;
import com.neordinary.domain.report.dto.ReportDetailResponse;
import com.neordinary.domain.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    // 보고서 생성
    @PostMapping
    public ResponseEntity<Long> createReport(@RequestBody ReportCreateRequest request) {
        Long reportId = reportService.createReport(request);
        return ResponseEntity.ok(reportId);
    }

    // 보고서 전체 조회
    @GetMapping
    public ResponseEntity<List<ReportDetailResponse>> getAllReports() {
        List<ReportDetailResponse> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    // 보고서 개별 조회
    @GetMapping("/{reportId}")
    public ResponseEntity<ReportDetailResponse> getReport(@PathVariable Long reportId) {
        ReportDetailResponse response = reportService.getReport(reportId);
        return ResponseEntity.ok(response);
    }
}


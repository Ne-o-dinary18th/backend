package com.neordinary.domain.report.service;

import com.neordinary.domain.receipt.Receipt;
import com.neordinary.domain.receipt.repository.ReceiptRepository;
import com.neordinary.domain.report.dto.ReportCreateRequest;
import com.neordinary.domain.report.dto.ReportDetailResponse;
import com.neordinary.domain.report.dto.ReportReceiptResponse;
import com.neordinary.domain.report.entity.Report;
import com.neordinary.domain.report.entity.ReportReceipt;
import com.neordinary.domain.report.repository.ReportRepository;
import com.neordinary.domain.tag.Tag;
import com.neordinary.domain.tag.repository.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportService {

    private final TagRepository tagRepository;
    private final ReceiptRepository receiptRepository;
    private final ReportRepository reportRepository;

    // 보고서 생성
    public Long createReport(ReportCreateRequest request) {

        Tag tag = tagRepository.findById(request.getTagId())
                .orElseThrow(() -> new RuntimeException("Tag not found"));

        List<Receipt> receipts = receiptRepository.findAllById(request.getReceiptIds());

        Report report = Report.builder()
                .tag(tag)
                .totalAmount(
                        receipts.stream().mapToLong(Receipt::getTotalAmount).sum()
                )
                .reportReceipts(new ArrayList<>())
                .build();

        for (Receipt r : receipts) {
            ReportReceipt rr = ReportReceipt.builder()
                    .report(report)
                    .storeName(r.getStoreName())
                    .date(r.getPurchaseDate())
                    .totalPrice(r.getTotalAmount())
                    .build();

            report.getReportReceipts().add(rr);
        }

        reportRepository.save(report);

        return report.getReportId();
    }

    // 보고서 전체 조회
    public List<ReportDetailResponse> getAllReports() {
        List<Report> reports = reportRepository.findAll();

        return reports.stream()
                .map(this::convertToDetail)
                .toList();
    }

    //보고서 개별 조회
    public ReportDetailResponse getReport(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        return convertToDetail(report);
    }

    // Report 엔티티 → ReportDetailResponse 변환
    private ReportDetailResponse convertToDetail(Report report) {

        Tag tag = report.getTag();

        return ReportDetailResponse.builder()
                .reportId(report.getReportId())
                .tagName(tag.getTitle())
                .reportDate(report.getCreatedAt().toLocalDate())
                .totalAmount(report.getTotalAmount())
                .receipts(
                        report.getReportReceipts().stream()
                                .map(rr -> ReportReceiptResponse.builder()
                                        .receiptId(rr.getId())
                                        .amount(rr.getTotalPrice())
                                        .date(rr.getDate())
                                        .storeName(rr.getStoreName())
                                        .build()
                                ).toList()
                )
                .build();
    }

}

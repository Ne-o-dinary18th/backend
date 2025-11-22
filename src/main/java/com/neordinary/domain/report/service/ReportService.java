package com.neordinary.domain.report.service;

import com.neordinary.domain.receipt.Receipt;
import com.neordinary.domain.receipt.respository.ReceiptRepository;
import com.neordinary.domain.report.dto.ReportCreateRequest;
import com.neordinary.domain.report.dto.ReportDetailResponse;
import com.neordinary.domain.report.dto.ReportReceiptResponse;
import com.neordinary.domain.report.entity.Report;
import com.neordinary.domain.report.repository.ReportRepository;
import com.neordinary.domain.tag.Tag;
import com.neordinary.domain.tag.repository.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportService {

    private final TagRepository tagRepository;
    private final ReceiptRepository receiptRepository;
    private final ReportRepository reportRepository;

    public Long createReport(ReportCreateRequest request) {

        Tag tag = tagRepository.findById(request.getTagId())
                .orElseThrow(() -> new RuntimeException("Tag not found"));

        List<Receipt> receipts = receiptRepository.findAllById(request.getReceiptIds());

        Report report = Report.builder()
                .tag(tag)
                .reportDate(LocalDate.now())
                .totalAmount(receipts.stream().mapToLong(Receipt::getTotalAmount).sum())
                .receipts(receipts)
                .build();

        reportRepository.save(report);

        return report.getReportId();
    }

    public List<ReportDetailResponse> getAllReports() {
        return reportRepository.findAll().stream()
                .map(this::convertToDetail)
                .toList();
    }

    public ReportDetailResponse getReport(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        return convertToDetail(report);
    }

    private ReportDetailResponse convertToDetail(Report report) {

        return ReportDetailResponse.builder()
                .reportId(report.getReportId())
                .tagName(report.getTag().getTitle())
                .reportDate(report.getReportDate())
                .totalAmount(report.getTotalAmount())
                .receipts(
                        report.getReceipts().stream()
                                .map(r -> ReportReceiptResponse.builder()
                                        .receiptId(r.getReceiptId())       // Receipt PK
                                        .amount(r.getTotalAmount())       // 총 금액
                                        .date(r.getPurchaseDate())        // 사용일자
                                        .storeName(r.getStoreName())      // 상호명
                                        .build()
                                ).toList()
                )
                .build();
    }
}

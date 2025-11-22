package com.neordinary.domain.report.service;

import com.neordinary.domain.receipt.Receipt;
import com.neordinary.domain.receipt.repository.ReceiptRepository;
import com.neordinary.domain.report.dto.ReportCreateRequest;
import com.neordinary.domain.report.dto.ReportDetailResponse;
import com.neordinary.domain.report.dto.ReportReceiptResponse;
import com.neordinary.domain.report.dto.ReportTagFilterResponse;
import com.neordinary.domain.report.entity.Report;
import com.neordinary.domain.report.repository.ReportRepository;
import com.neordinary.domain.tag.Tag;
import com.neordinary.domain.tag.repository.TagRepository;
import com.neordinary.global.apiPayload.code.status.ErrorStatus;
import com.neordinary.global.apiPayload.exception.GeneralException;
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
                .orElseThrow(() -> new GeneralException(ErrorStatus.TAG_NOT_FOUND));

        List<Long> requestReceiptIds = request.getReceiptIds();

        List<Receipt> receipts = receiptRepository.findAllById(requestReceiptIds);

        // 영수증 수량 검증 (ID가 아예 존재하지 않는 경우)
        if (receipts.size() != requestReceiptIds.size()) {
            throw new GeneralException(ErrorStatus.RECEIPT_NOT_FOUND);
        }

        // 해당 태그에 속하는지 검증
        boolean invalid = receipts.stream()
                .anyMatch(r -> !r.getTag().getTagId().equals(tag.getTagId()));

        if (invalid) {
            throw new GeneralException(ErrorStatus.RECEIPT_NOT_IN_TAG);
        }

        // 정상 케이스 → 보고서 생성
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
                .orElseThrow(() -> new GeneralException(ErrorStatus.TAG_NOT_FOUND));

        return convertToDetail(report);
    }

    // 태그가 존재하지않을 경우 : 예외처리 & 태그가 존재하는데 보고서 0개 : 빈배열
    public List<ReportTagFilterResponse> getReportsByTagId(Long tagId) {

        // 태그 존재 여부 체크 (예외)
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.TAG_NOT_FOUND));

        // 태그는 존재하지만 보고서가 없을 수 있음 → 정상, 빈 리스트 반환
        List<Report> reports = reportRepository.findAllByTag_TagId(tagId);

        return reports.stream()
                .map(r -> ReportTagFilterResponse.builder()
                        .reportId(r.getReportId())
                        .reportDate(r.getReportDate())
                        .tagName(tag.getTitle())
                        .totalAmount(r.getTotalAmount())
                        .build()
                ).toList();
    }


    private ReportDetailResponse convertToDetail(Report report) {

        Tag tag = report.getTag();

        return ReportDetailResponse.builder()
                .reportId(report.getReportId())
                .tagName(report.getTag().getTitle())
                .reportDate(report.getReportDate())

                // 관리 정보 추가
                .managerName(tag.getManagerName())
                .managerAccount(tag.getManagerAccount())

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

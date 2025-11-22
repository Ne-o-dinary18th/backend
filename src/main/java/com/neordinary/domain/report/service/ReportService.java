//package com.neordinary.domain.report.service;
//
//import com.neordinary.domain.receipt.Receipt;
//import com.neordinary.domain.receipt.respository.ReceiptRepository;
//import com.neordinary.domain.report.dto.ReportCreateRequest;
//import com.neordinary.domain.report.entity.Report;
//import com.neordinary.domain.report.entity.ReportReceipt;
//import com.neordinary.domain.report.repository.ReportRepository;
//import com.neordinary.domain.tag.Tag;
//import com.neordinary.domain.tag.repository.TagRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.crossstore.ChangeSetPersister;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class ReportService {
//
//    private final TagRepository tagRepository;
//    private final ReceiptRepository receiptRepository;
//    private final ReportRepository reportRepository;
//
//    public Long createReport(ReportCreateRequest request) {
//
//        Tag tag = tagRepository.findById(request.getTagId())
//                .orElseThrow(() -> new ChangeSetPersister.NotFoundException("Tag not found"));
//
//        List<Receipt> receipts = receiptRepository.findAllById(request.getReceiptIds());
//
////        Report report = Report.builder()
////                .tag(tag)
////                .totalAmount(
////                        receipts.stream().mapToInt(Receipt::getTotalPrice).sum()
////                )
////                .reportReceipts(new ArrayList<>())
////                .build();
//
////        for (Receipt r : receipts) {
////            ReportReceipt rr = ReportReceipt.builder()
////                    .report(report)
////                    .storeName(r.getStoreName())
////                    .date(r.getDate())
////                    .totalPrice(r.getTotalPrice())
////                    .build();
////
////            report.getReportReceipts().add(rr);
////        }
//
//        reportRepository.save(report);
//
//        return report.getReportId();
//    }
//}

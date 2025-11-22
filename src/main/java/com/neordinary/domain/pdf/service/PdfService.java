package com.neordinary.domain.pdf.service;

import com.neordinary.domain.report.dto.ReportDetailResponse;
import com.neordinary.domain.report.dto.ReportReceiptResponse;
import com.neordinary.domain.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
@Service
@RequiredArgsConstructor
public class PdfService {

    private final ReportService reportService;

    public byte[] generateReportPdf(Long reportId) throws IOException {

        ReportDetailResponse report = reportService.getReport(reportId);

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // 한글 폰트 로드
        PDType0Font font = PDType0Font.load(document,
                getClass().getResourceAsStream("/fonts/NanumGothic-Bold.ttf"));

        PDPageContentStream content = new PDPageContentStream(document, page);

        float y = 750;
        float margin = 40;

        // ===========================
        // 제목
        // ===========================
        content.beginText();
        content.setFont(font, 18);
        content.setLeading(22f);
        content.newLineAtOffset(margin, y);
        content.showText("태그 보고서 - " + report.getTagName());
        content.endText();

        y -= 40;

        // ===========================
        // 상단 정보
        // ===========================
        content.beginText();
        content.setFont(font, 12);
        content.setLeading(16f);
        content.newLineAtOffset(margin, y);

        content.showText("보고서 ID: " + report.getReportId()); content.newLine();
        content.showText(report.getReportDate() + "일 보고서"); content.newLine();
        content.showText("관리자: " + report.getManagerName()); content.newLine();
        content.showText("관리 계좌: " + report.getManagerAccount()); content.newLine();
        content.showText("--------------------------------"); content.newLine();

        content.endText();
        y -= 80;

        // ===========================
        // 영수증 정보
        // ===========================
        for (ReportReceiptResponse r : report.getReceipts()) {

            content.beginText();
            content.setFont(font, 12);
            content.setLeading(16f);
            content.newLineAtOffset(margin, y);

            content.showText("상호명: " + r.getStoreName()); content.newLine();
            content.showText("날짜: " + r.getDate() + " | 금액: " + r.getAmount() + "원");
            content.newLine();
            content.newLine();
            content.endText();

            y -= 60;

            if (y < 100) {
                content.close();
                page = new PDPage();
                document.addPage(page);
                content = new PDPageContentStream(document, page);
                y = 750;
            }
        }

        content.beginText();
        content.setFont(font, 14);
        content.setLeading(16f);
        content.newLineAtOffset(margin, y);
        content.showText("총 지출 금액: " + report.getTotalAmount() + "원");
        content.endText();

        content.close();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        document.save(baos);
        document.close();

        return baos.toByteArray();
    }
}

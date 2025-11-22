package com.neordinary.domain.pdf.controller;

import com.neordinary.domain.pdf.service.PdfService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class downloadReportPdfController {

    private final PdfService pdfService;

    @Operation(summary = "태그별보고서 pdf 생성", description = "각 태그별로 생성된 보고서를 pdf로 저장해줍니다.")
    @GetMapping("/{reportId}/pdf")
    public ResponseEntity<byte[]> downloadReportPdf(@PathVariable Long reportId) throws IOException {
        byte[] pdfBytes = pdfService.generateReportPdf(reportId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report_" + reportId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}

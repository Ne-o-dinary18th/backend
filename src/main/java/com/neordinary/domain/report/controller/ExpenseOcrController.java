package com.neordinary.domain.report.controller;

import com.neordinary.domain.report.dto.OcrExpenseRequest;
import com.neordinary.domain.report.dto.OcrExpenseResponse;
import com.neordinary.domain.report.service.ExpenseOcrService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenses")
@Tag(name = "Expense OCR API", description = "OCR 텍스트 기반 지출내역 생성 API")
public class ExpenseOcrController {

    private final ExpenseOcrService expenseOcrService;

    @Operation(
            summary = "OCR 텍스트로 지출내역 생성",
            description = "프론트에서 OCR 처리 후 전달된 원문 텍스트를 기반으로 " +
                    "상호/일자/총금액 값을 파싱하여 지출내역을 저장합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "파싱 및 저장 성공",
                    content = @Content(schema = @Schema(implementation = OcrExpenseResponse.class)))
    })
    @PostMapping("/ocr")
    public OcrExpenseResponse createExpense(@RequestBody OcrExpenseRequest request) {
        return expenseOcrService.createExpenseFromOcr(request);
    }
}


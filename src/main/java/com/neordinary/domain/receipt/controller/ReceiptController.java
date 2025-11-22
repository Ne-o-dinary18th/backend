package com.neordinary.domain.receipt.controller;

import com.neordinary.domain.receipt.dto.req.ReceiptRequest;
import com.neordinary.domain.receipt.Receipt;
import com.neordinary.domain.receipt.dto.res.ReceiptResponse;
import com.neordinary.domain.receipt.service.command.ReceiptCommandService;
import com.neordinary.domain.receipt.service.query.ReceiptQueryService;
import com.neordinary.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api")
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptCommandService receiptCommandService;
    private final ReceiptQueryService receiptQueryService;

    /**
     * /api/receipts/upload
     * 영수증 생성하기 API
     *
     */
    @PostMapping("/receipts/upload")
    public ApiResponse<ReceiptResponse.UploadDTO> uploadReceipt(
            @RequestBody @Valid ReceiptRequest.UploadDTO dto
    ){
        return ApiResponse.onSuccess(receiptCommandService.uploadReceipt(dto));
    }

    /**
     * /api/receipts/{receiptId}
     * 영수증 상세페이지 조회(리스트에서 클릭시)
     *
     */

    @GetMapping("/receipts/{receiptId}")
    public ApiResponse<ReceiptResponse.ReceiptResponseDTO> getReceipt(
            @PathVariable Long receiptId
    ){
       return ApiResponse.onSuccess(receiptQueryService.getReceipt(receiptId));
    }

    /**
     * /api/receipts/all
     * 영수증 총액 조회
     * 단일/복수 둘 다 가능
     *
     */
    @GetMapping("/receipts/all")
    public ApiResponse<Long> getTotalAmount(
            @RequestParam List<Long> receiptIds
    ){
        return ApiResponse.onSuccess(receiptQueryService.getTotalAmount(receiptIds));
    }

    /**
     * /api/receipts/{receiptId}
     * 영수증 삭제
     *
     */
    @DeleteMapping("/receipts/{receiptId}")
    public ApiResponse<Long> deleteReceipt(
            @PathVariable Long receiptId
    ){
        return ApiResponse.onSuccess(receiptCommandService.deleteReceipt(receiptId));
    }
}

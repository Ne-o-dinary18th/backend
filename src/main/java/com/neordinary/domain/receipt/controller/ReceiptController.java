package com.neordinary.domain.receipt.controller;

import com.neordinary.domain.receipt.dto.req.ReceiptRequest;
import com.neordinary.domain.receipt.Receipt;
import com.neordinary.domain.receipt.dto.res.ReceiptResponse;
import com.neordinary.domain.receipt.exception.code.ReceiptCode;
import com.neordinary.domain.receipt.service.command.ReceiptCommandService;
import com.neordinary.domain.receipt.service.query.ReceiptQueryService;
import com.neordinary.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api")
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptCommandService receiptCommandService;
    private ReceiptQueryService receiptQueryService;

    @PostMapping("/receipts/upload")
    public ApiResponse<ReceiptResponse.UploadDTO> uploadReceipt(
            @RequestBody ReceiptRequest.UploadDTO dto
    ){
        return ApiResponse.onSuccess(receiptCommandService.uploadReceipt(dto));
    }

    @GetMapping("/receipts/{receiptId}")
    public ApiResponse<Receipt> getReceipt(
            @PathVariable Long receiptId
    ){
       return ApiResponse.onSuccess(receiptCommandService.getReceipt(receiptId));
    }

    @GetMapping("/receipts/all")
    public ApiResponse<List<Receipt>> getTotalAmount(){
        return ApiResponse.onSuccess(receiptCommandService.getAllReceipt());
    }

    @DeleteMapping("/receipts/{receiptId}")
    public ApiResponse<Long> deleteReceipt(){
        // return ApiResponse.onSuccess(receiptCommandService.deleteReceipt());
        return null;
    }
}

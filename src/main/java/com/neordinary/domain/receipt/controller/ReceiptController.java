package com.neordinary.domain.receipt.controller;

import com.neordinary.domain.receipt.dto.req.ReceiptRequest;
import com.neordinary.domain.receipt.entity.Receipt;
import com.neordinary.domain.receipt.exception.code.ReceiptCode;
import com.neordinary.domain.receipt.service.command.ReceiptCommandService;
import com.neordinary.domain.receipt.service.query.ReceiptQueryService;
import com.neordinary.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController("/api")
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptCommandService receiptCommandService;
    private ReceiptQueryService receiptQueryService;

    @PostMapping("/receipts/upload")
    public ApiResponse<Receipt> uploadReceipt(
            @RequestBody ReceiptRequest.UploadRequestDTO dto
    ){
        return ApiResponse.onSuccess(ReceiptCode.FOUND, receiptCommandService.signup(dto));
    }

    @GetMapping("/receipts/{receiptId}}")
    public ApiResponse<Receipt> getReceipt(){
        return null;
    }

    @GetMapping("/receipts/{receiptId}}")
    public ApiResponse<Receipt> getTotalAmount(){
        return null;
    }

    @DeleteMapping("/receipts/{receiptId}}")
    public ApiResponse<Receipt> deleteReceipt(){
        return null;
    }
}

package com.neordinary.domain.receipt.service.query;

import com.neordinary.domain.receipt.Receipt;
import com.neordinary.domain.receipt.dto.res.ReceiptResponse;

import java.util.List;


public interface ReceiptQueryService {
    public ReceiptResponse.ReceiptResponseDTO getReceipt(Long receiptId);

    public Long getTotalAmount(List<Long> receiptIds);
}

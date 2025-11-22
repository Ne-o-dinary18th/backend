package com.neordinary.domain.receipt.service.query;

import com.neordinary.domain.receipt.Receipt;

import java.util.List;

public interface ReceiptQueryService {
    public Receipt getReceipt(Long receiptId);

    public List<Receipt> getAllReceipt();
}

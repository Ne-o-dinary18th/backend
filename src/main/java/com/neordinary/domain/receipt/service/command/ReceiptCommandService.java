package com.neordinary.domain.receipt.service.command;

import com.neordinary.domain.receipt.dto.res.ReceiptResponse;

public interface ReceiptCommandService {
    public ReceiptResponse.UploadDTO uploadReceipt(ReceiptResponse.UploadDTO dto);
}

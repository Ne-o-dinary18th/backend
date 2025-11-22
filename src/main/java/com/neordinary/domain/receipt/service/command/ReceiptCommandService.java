package com.neordinary.domain.receipt.service.command;

import com.neordinary.domain.receipt.Receipt;
import com.neordinary.domain.receipt.dto.req.ReceiptRequest;
import com.neordinary.domain.receipt.dto.res.ReceiptResponse;

import java.util.List;

public interface ReceiptCommandService {

    public ReceiptResponse.UploadDTO uploadReceipt(ReceiptRequest.UploadDTO dto);

    // public Receipt deleteReceipt();

}

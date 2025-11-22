package com.neordinary.domain.receipt.service.command;

import com.neordinary.domain.receipt.Receipt;
import com.neordinary.domain.receipt.dto.req.ReceiptRequest;
import com.neordinary.domain.receipt.dto.res.ReceiptResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReceiptCommandService {

    public ReceiptResponse.UploadDTO uploadReceipt(Long tagId, String ocrText, MultipartFile image);

    public Long deleteReceipt(Long receiptId);

}

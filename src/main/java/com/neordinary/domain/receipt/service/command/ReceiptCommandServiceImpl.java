package com.neordinary.domain.receipt.service.command;

import com.neordinary.domain.receipt.dto.res.ReceiptResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceiptCommandServiceImpl implements ReceiptCommandService{

    public ReceiptResponse.UploadDTO uploadReceipt(ReceiptResponse.UploadDTO dto){
        return null;
    }
}

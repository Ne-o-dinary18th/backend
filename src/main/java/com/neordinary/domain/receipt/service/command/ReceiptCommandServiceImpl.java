package com.neordinary.domain.receipt.service.command;

import com.neordinary.domain.receipt.Receipt;
import com.neordinary.domain.receipt.converter.ReceiptConverter;
import com.neordinary.domain.receipt.dto.req.ReceiptRequest;
import com.neordinary.domain.receipt.dto.res.ReceiptResponse;
import com.neordinary.domain.receipt.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceiptCommandServiceImpl implements ReceiptCommandService{

    private static ReceiptRepository receiptRepository;

    @Override
    public ReceiptResponse.UploadDTO uploadReceipt(ReceiptRequest.UploadDTO dto){
        Receipt entity = ReceiptConverter.toReceipt(dto);
        receiptRepository.save(entity);

        return ReceiptConverter.toUploadDTO(entity);
    }


}

package com.neordinary.domain.receipt.converter;

import com.neordinary.domain.receipt.Receipt;
import com.neordinary.domain.receipt.dto.req.ReceiptRequest;
import com.neordinary.domain.receipt.dto.res.ReceiptResponse;
import com.neordinary.domain.tag.Tag;

public class ReceiptConverter {

    // entity -> DTO
    public static ReceiptResponse.UploadDTO toUploadDTO(
            Receipt entity
    ){
        return ReceiptResponse.UploadDTO.builder()
                .receiptId(entity.getReceiptId())
                .build();
    }

    // DTO -> entity
    public static Receipt toReceipt(
            ReceiptRequest.UploadDTO dto,
            Tag tag
    ){
        return Receipt.builder()
                .storeName(dto.storeName)
                .purchaseDate(dto.purchaseDate)
                .totalAmount(dto.totalAmount)
                .tag(tag)
                .build();
    }
}

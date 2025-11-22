package com.neordinary.domain.receipt.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ReceiptResponse {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class UploadDTO{
        Long receiptId;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ReceiptResponseDTO{

    }

}

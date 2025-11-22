package com.neordinary.domain.receipt.dto.res;

import lombok.Builder;

public class ReceiptResponse {

    @Builder
    public static class UploadDTO{
        Long receiptId;
    }
}

package com.neordinary.domain.receipt.dto.res;

import lombok.Builder;

@Builder
public class ReceiptResponse {

    public static class UploadDTO{
        Long receiptId;
    }
}

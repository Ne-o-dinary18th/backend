package com.neordinary.domain.receipt.dto.res;

import com.neordinary.domain.receipt.Receipt;
import lombok.Builder;

import java.util.List;


public class ReceiptResponse {

    @Builder
    public static class UploadDTO{
        Long receiptId;
    }
}

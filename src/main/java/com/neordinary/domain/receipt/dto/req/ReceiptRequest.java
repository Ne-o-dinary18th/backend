package com.neordinary.domain.receipt.dto.req;

import java.time.LocalDate;

public class ReceiptRequest {

    public static class UploadDTO{
        // 태그 ID
        public Long tagId;

        // 영수증 OCR 파싱 값
        public String storeName;
        public LocalDate purchaseDate;
        public Long totalAmount;

        // 영수증 원본
    }
}

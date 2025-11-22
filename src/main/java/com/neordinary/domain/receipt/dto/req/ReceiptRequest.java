package com.neordinary.domain.receipt.dto.req;

import java.time.LocalDate;

public class ReceiptRequest {

    public static class UploadDTO{
        public String storeName;
        public LocalDate purchaseDate;
        public Long totalAmount;
        public Long tagId;
    }
}

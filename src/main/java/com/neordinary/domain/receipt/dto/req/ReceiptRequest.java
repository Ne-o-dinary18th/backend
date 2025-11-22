package com.neordinary.domain.receipt.dto.req;

public class ReceiptRequest {

    public static class UploadRequestDTO{
        private Long tagId;
        private String storeName;
        private String purchaseDate;
        private Long totalAmount;
    }
}

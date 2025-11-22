package com.neordinary.domain.receipt.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
public class ReceiptRequest {

    @Getter
    @AllArgsConstructor
    public static class OcrTextRequest {
        // 태그
        String text;
    }

    @Getter
    @AllArgsConstructor
    public static class UploadDTO{
        // 태그 ID
        public Long tagId;

        // 영수증 OCR 파싱 값
        public String storeName;
        public String purchaseDate;
        public Long totalAmount;
    }
}

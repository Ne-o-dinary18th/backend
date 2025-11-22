package com.neordinary.domain.tag.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TagResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TagDto {
        Long tagId;
        String tagName;
        // @ExistCategoryColors

        public static TagResponseDto.TagDto convertToTagDto(Long tagId, String tagName) {
            return TagResponseDto.TagDto.builder()
                    .tagId(tagId)
                    .tagName(tagName)
                    .build();

        }
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TagListDto {
        List<TagDto> tags;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReceiptDto {
        Long receiptId;
        String storeName;
        String purchaseDate; // 일자
        Long totalAmount;

        public static TagResponseDto.ReceiptDto convertToTagDetail(
                Long receiptId, String storeName, String purchaseDate, Long totalAmount) {
            return ReceiptDto.builder()
                    .receiptId(receiptId)
                    .storeName(storeName)
                    .purchaseDate(purchaseDate)
                    .totalAmount(totalAmount)
                    .build();

        }
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TagDetailDto {
        Long tagId;
        String tagName;
        Integer totalAmount;
        Integer totalUsers;
        String userName;
        String userAccount;
        List<ReceiptDto> receipts;
    }
    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TagReceiptListDto {
        Long tagId;
        String tagName;
        List<ReceiptDto> receipts;
    }
}

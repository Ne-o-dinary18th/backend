package com.neordinary.domain.receipt.service.query;

import com.neordinary.domain.receipt.Receipt;
import com.neordinary.domain.receipt.dto.res.ReceiptResponse;
import com.neordinary.domain.receipt.repository.ReceiptRepository;
import com.neordinary.domain.tag.Tag;
import com.neordinary.global.apiPayload.code.status.ErrorStatus;
import com.neordinary.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiptQueryServiceImpl implements ReceiptQueryService{

    private final ReceiptRepository receiptRepository;

    // 영수증 조회하기
    @Override
    @Transactional(readOnly = true)
    public ReceiptResponse.ReceiptResponseDTO getReceipt(Long receiptId) {
        Receipt receipt = receiptRepository.findById(receiptId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.RECEIPT_NOT_FOUND));

        return ReceiptResponse.ReceiptResponseDTO.builder()
                .receiptId(receipt.getReceiptId())
                .storeName(receipt.getStoreName())
                .purchaseDate(receipt.getPurchaseDate())
                .totalAmount(receipt.getTotalAmount())
                .build();
    }

    // 영수증 총액 계산
    @Override
    @Transactional(readOnly = true)
    public Long getTotalAmount(List<Long> receiptIds) {
        List<Receipt> receipts = receiptRepository.findAllById(receiptIds);

        if (receipts.size() != receiptIds.size()) {
            throw new GeneralException(ErrorStatus.RECEIPT_NOT_FOUND);
        }

        return receipts.stream()
                .mapToLong(Receipt::getTotalAmount)
                .sum();
    }
}

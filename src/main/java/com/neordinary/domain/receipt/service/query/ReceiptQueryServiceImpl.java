package com.neordinary.domain.receipt.service.query;

import com.neordinary.domain.receipt.Receipt;
import com.neordinary.domain.receipt.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceiptQueryServiceImpl implements ReceiptQueryService{

    private final ReceiptRepository receiptRepository;

    // 영수증 조회하기
    @Override
    public Receipt getReceipt(Long receiptId) {
        Optional<Receipt> findReceipt = receiptRepository.findById(receiptId);
        return findReceipt.orElse(null);
    }

    // 영수증 총액 계산
    @Override
    public Long getTotalAmount(List<Long> receiptIds) {
        List<Receipt> receipts = receiptRepository.findAllById(receiptIds);
        return receipts.stream()
                .mapToLong(Receipt::getTotalAmount)
                .sum();
    }
}

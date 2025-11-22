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

    private static ReceiptRepository receiptRepository;

    @Override
    public Receipt getReceipt(Long receiptId) {
        Optional<Receipt> findReceipt = receiptRepository.findById(receiptId);
        return findReceipt.orElse(null);
    }

    @Override
    public List<Receipt> getAllReceipt() {
        return receiptRepository.findAll();
    }
}

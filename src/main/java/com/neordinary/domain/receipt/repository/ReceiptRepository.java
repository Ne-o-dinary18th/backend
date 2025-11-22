package com.neordinary.domain.receipt.repository;

import com.neordinary.domain.receipt.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> findReceiptsByTag_TagId(Long tagId);
}

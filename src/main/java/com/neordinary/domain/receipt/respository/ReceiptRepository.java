package com.neordinary.domain.receipt.respository;

import com.neordinary.domain.receipt.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}


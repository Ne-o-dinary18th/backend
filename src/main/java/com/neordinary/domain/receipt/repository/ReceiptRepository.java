package com.neordinary.domain.receipt.repository;

import com.neordinary.domain.receipt.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> findReceiptsByTag_TagId(Long tagId);
}

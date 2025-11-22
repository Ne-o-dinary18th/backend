package com.neordinary.domain.report.repository;

import com.neordinary.domain.report.entity.Report;
import com.neordinary.domain.report.entity.ReportReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportReceiptRepository extends JpaRepository<ReportReceipt, Long> {

    List<ReportReceipt> findAllByReport(Report report);
}

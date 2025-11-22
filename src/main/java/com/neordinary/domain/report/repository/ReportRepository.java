package com.neordinary.domain.report.repository;

import com.neordinary.domain.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByTag_TagId(Long tagId);
}

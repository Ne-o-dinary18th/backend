package com.neordinary.domain.report.entity;

import com.neordinary.domain.common.BaseEntity;
import com.neordinary.domain.receipt.Receipt;
import com.neordinary.domain.tag.Tag;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "reports")
public class Report extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reports_id")
    private Long reportId;

    @ManyToOne
    @JoinColumn(name = "tags_id", nullable = false)
    private Tag tag;

    private Long totalAmount;
    private LocalDate reportDate;

    @ManyToMany
    @JoinTable(
            name = "report_receipt",   // 새 조인 테이블
            joinColumns = @JoinColumn(name = "report_id"),
            inverseJoinColumns = @JoinColumn(name = "receipt_id")
    )
    private List<Receipt> receipts = new ArrayList<>();
}



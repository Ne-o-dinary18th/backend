package com.neordinary.domain.report.entity;

import com.neordinary.domain.receipt.Receipt;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "report_receipts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

    @ManyToOne @JoinColumn(name = "receipt_id")
    private Receipt receipt;
}

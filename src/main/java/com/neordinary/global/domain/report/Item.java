package com.neordinary.global.domain.report;

import com.neordinary.global.domain.receipt.Receipt;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "items_id")
    private Long itemId;

    private String name;
    private Long unitAmount;
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipts_id", nullable = false)
    private Receipt receipt;
}


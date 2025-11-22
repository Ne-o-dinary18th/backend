package com.neordinary.domain.receipt.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId; // 품목 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiptId")
    private Receipt receipt; // 영수증

    @Column
    private String name; // 품명

    @Column
    private Long unitAmount; // 단가

    @Column
    private Long quantity; // 수량
}

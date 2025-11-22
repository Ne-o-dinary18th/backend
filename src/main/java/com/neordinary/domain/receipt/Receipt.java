package com.neordinary.domain.receipt;

import com.neordinary.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class Receipt extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptId; // 영수증 아이디

    @Column
    private String storeName; // 상호명

    @Column
    private LocalDate purchaseDate; // 일자

    @Column
    private Long totalAmount; // 총액

    @Column
    private String imageUrl; // 영수증사진Url
}

package com.neordinary.global.domain.receipt;

import com.neordinary.global.domain.common.BaseEntity;
import com.neordinary.global.domain.tag.Tag;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "receipts")
public class Receipt extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipts_id")
    private Long receiptId;

    private String storeName;
    private LocalDate purchaseDate;
    private Long totalAmount;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tags_id", nullable = false)
    private Tag tag;
}

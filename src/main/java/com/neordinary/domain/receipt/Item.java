package com.neordinary.domain.receipt;

import com.neordinary.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "receiptId")
    private Receipt receipt;

    @Column
    private String name;

    @Column
    private Long unitAmount;

    @Column
    private Long quantity;
}

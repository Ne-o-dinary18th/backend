package com.neordinary.domain.receipt;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptId; // 영수증 아이디

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "tagId")
    //private Tag tag; // 태그

    @Column
    private String storeName; // 상호명

    @Column
    private LocalDate purchaseDate; // 일자

    @Column
    private Long totalAmount; // 총액

    @Column
    private String imageUrl; // 영수증사진Url

    /*
    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Item> items = new ArrayList<>();
    */
}

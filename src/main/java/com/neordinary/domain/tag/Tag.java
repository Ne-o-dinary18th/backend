package com.neordinary.domain.tag;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tags_id")
    private Long tagId;

    @Column(nullable = false)
    private String title;

    private String managerName; // 관리자 이름
    private String managerAccount; //관리 계좌
}

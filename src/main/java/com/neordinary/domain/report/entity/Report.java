package com.neordinary.domain.report.entity;

import com.neordinary.domain.common.BaseEntity;
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
    private Long reportId;

    @ManyToOne
    @JoinColumn(name = "tags_id", nullable = false)
    private Tag tag;

    private Long totalAmount; // 보고서 총합
    private LocalDate reportDate; // 보고서 생성 기준 날짜

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    private List<ReportReceipt> reportReceipts = new ArrayList<>();
}



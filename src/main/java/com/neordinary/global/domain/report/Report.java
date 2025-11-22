package com.neordinary.global.domain.report;

import com.neordinary.global.domain.common.BaseEntity;
import com.neordinary.global.domain.tag.Tag;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
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
}


package com.neordinary.domain.tag;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long userId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "tags_id", nullable = false)
    private Tag tag;
}


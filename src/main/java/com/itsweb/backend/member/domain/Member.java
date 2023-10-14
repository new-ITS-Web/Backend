package com.itsweb.backend.member.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String userId;

    @Column(unique = true)
    private String username;

    private String password;

    public Member(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }
}

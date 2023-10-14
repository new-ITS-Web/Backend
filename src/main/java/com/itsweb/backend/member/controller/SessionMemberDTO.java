package com.itsweb.backend.member.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class SessionMemberDTO implements Serializable{
    private String userId;
    private String username;
    @Serial
    private static final long serialVersionUID = -3567944554984614866L;
}

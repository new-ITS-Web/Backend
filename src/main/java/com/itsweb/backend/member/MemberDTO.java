package com.itsweb.backend.member;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    @NotBlank
    private String userId;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}

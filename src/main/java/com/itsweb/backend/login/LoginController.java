package com.itsweb.backend.login;


import com.itsweb.backend.member.Member;
import com.itsweb.backend.member.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@ModelAttribute MemberDTO memberDTO, HttpServletRequest request) {
        String userId = memberDTO.getUserId();
        String password = memberDTO.getPassword();
        Member loginMember = loginService.loginCheck(userId, password);

        if (loginMember == null) {
            return ResponseEntity.badRequest().body("아이디 또는 비밀번호 오류입니다.");
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginMember", loginMember);

        return ResponseEntity.ok().body("로그인");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok().body("로그아웃");

    }
}

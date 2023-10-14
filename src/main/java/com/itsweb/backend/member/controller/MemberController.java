package com.itsweb.backend.member.controller;

import com.itsweb.backend.member.SessionConst;
import com.itsweb.backend.member.domain.Member;
import com.itsweb.backend.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@Valid @RequestBody MemberDTO memberDTO) {
        Member member = memberService.encodePassword(memberDTO);
        return memberService.join(member);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        Member loginMember = memberService.loginCheck(loginDTO.getUserId(), loginDTO.getPassword());
        if (loginMember == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호 오류입니다.");
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, new SessionMemberDTO(loginMember.getUserId(), loginMember.getUsername()));
        return ResponseEntity.ok().body("로그인");
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok().body("로그아웃");

    }

    @GetMapping("/mypage")
    public ResponseEntity<String> myPage(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) SessionMemberDTO loginMember) {
        if (loginMember == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그인이 필요합니다");
        }
        return ResponseEntity.ok().body(loginMember.getUserId());
    }

}

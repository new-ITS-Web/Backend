package com.itsweb.backend.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    @Transactional
    public ResponseEntity<?> join(@Valid @RequestBody MemberDTO memberDTO) {
        Member member = new Member();
        member.signUp(memberDTO.getUserId(), memberDTO.getUsername(), memberDTO.getPassword());
        if (memberService.isDuplicated(member)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("아이디 또는 닉네임 중복입니다");
        }
        memberService.save(member);
        return ResponseEntity.ok().body("회원가입 성공");
    }

}

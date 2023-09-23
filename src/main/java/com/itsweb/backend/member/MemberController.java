package com.itsweb.backend.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@ModelAttribute MemberDTO memberDTO) {
        log.info("memberDTO={}", memberDTO);
        Member member = new Member();
        member.signUpMember(memberDTO.getUserId(),memberDTO.getUsername(), memberDTO.getPassword());
        memberService.save(member);
        return new ResponseEntity<>("회원가입 성공", HttpStatus.OK);
    }

}

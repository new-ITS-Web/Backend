package com.itsweb.backend.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

        memberService.save(member);

        return ResponseEntity.ok().body("회원가입 성공");
    }

    @GetMapping("/join/check-userId/{userId}")
    public ResponseEntity<Boolean> checkUserId(@PathVariable String userId) {
        return ResponseEntity.ok(memberService.checkUserIdDuplicated(userId));
    }

    @GetMapping("/join/check-username/{username}")
    public ResponseEntity<Boolean> checkUsername(@PathVariable String username) {
        return ResponseEntity.ok(memberService.checkUserIdDuplicated(username));
    }
}

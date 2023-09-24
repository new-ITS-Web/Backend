package com.itsweb.backend.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@Validated @ModelAttribute MemberDTO memberDTO, BindingResult bindingResult) {
        Member member = new Member();
        member.signUp(memberDTO.getUserId(),memberDTO.getUsername(), memberDTO.getPassword());
        memberService.save(member);
        if (bindingResult.hasErrors()) {
            // 검증 실패 시 오류 메시지를 처리
            List<FieldError> errors = bindingResult.getFieldErrors();
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : errors) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
        }
        return ResponseEntity.ok().body("회원가입 성공");
    }

}

package com.itsweb.backend.member;

import com.itsweb.backend.ValidationErrorHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final ValidationErrorHandler validationErrorHandler;
    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@Validated @RequestBody MemberDTO memberDTO, BindingResult bindingResult) {
        Member member = new Member();
        member.signUp(memberDTO.getUserId(), memberDTO.getUsername(), memberDTO.getPassword());
        //아이디, 유저네임 중복 검증
        memberService.save(member);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = validationErrorHandler.errorHandler(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
        }
        return ResponseEntity.ok().body("회원가입 성공");
    }

}

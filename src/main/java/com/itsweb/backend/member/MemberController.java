package com.itsweb.backend.member;

import com.itsweb.backend.ValidationErrorHandler;
import com.itsweb.backend.exception.ErrorResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final ValidationErrorHandler validationErrorHandler;
    private final MemberService memberService;

    @PostMapping("/join")
    @Transactional
    public ResponseEntity<?> join(@Valid @RequestBody MemberDTO memberDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = validationErrorHandler.handleError(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
        }

        Member member = new Member();
        member.signUp(memberDTO.getUserId(), memberDTO.getUsername(), memberDTO.getPassword());

        // 아이디, 유저네임 중복 검증
        memberService.save(member);

        return ResponseEntity.ok().body("회원가입 성공");
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> SqlExHandle(SQLException e) {
        log.error("[exceptionHandle] ex", e);
        ErrorResult errorResult = new ErrorResult("ex", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

}

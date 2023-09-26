package com.itsweb.backend.login;

import com.itsweb.backend.ValidationErrorHandler;
import com.itsweb.backend.member.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final ValidationErrorHandler validationErrorHandler;
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginDTO loginDTO, HttpServletRequest request, BindingResult bindingResult) {
        String userId = loginDTO.getUserId();
        String password = loginDTO.getPassword();
        Member loginMember = loginService.loginCheck(userId, password);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = validationErrorHandler.handleError(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
        }

        if (loginMember == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호 오류입니다.");
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

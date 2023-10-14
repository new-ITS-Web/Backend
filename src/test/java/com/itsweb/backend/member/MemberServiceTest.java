package com.itsweb.backend.member;

import com.itsweb.backend.member.controller.MemberDTO;
import com.itsweb.backend.member.domain.Member;
import com.itsweb.backend.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired PasswordEncoder passwordEncoder;

    @Test
    void 회원가입성공() {
        //given
        MemberDTO memberDTO = new MemberDTO("test", "test", "1234");
        Member member = memberService.encodePassword(memberDTO);

        //when
        ResponseEntity<?> responseEntity = memberService.join(member);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void 중복회원가입() {
        MemberDTO memberDTO1 = new MemberDTO("2", "2", "1234");
        MemberDTO memberDTO2 = new MemberDTO("2", "2", "1234");
        Member member1 = memberService.encodePassword(memberDTO1);
        Member member2 = memberService.encodePassword(memberDTO2);

        memberService.join(member1);
        ResponseEntity<?> responseEntity = memberService.join(member2);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void 로그인성공() {

    }

    @Test
    void 아이디비밀번호오류() {
        MemberDTO memberDTO = new MemberDTO("test", "test", "1234");
        memberService.encodePassword(memberDTO);

        Member member1 = memberService.loginCheck("test", "1212");
        Member member2 = memberService.loginCheck("ttest", "1234");

        assertThat(member1).isNull();
        assertThat(member2).isNull();
    }
}
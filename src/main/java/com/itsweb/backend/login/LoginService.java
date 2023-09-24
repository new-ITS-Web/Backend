package com.itsweb.backend.login;

import com.itsweb.backend.member.Member;
import com.itsweb.backend.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Member loginCheck(String userId, String password) {
        Member findMember = memberRepository.findByUserId(userId);

        //로그인 실패
        if (findMember == null || !findMember.getPassword().equals(password)) {
            return null;
        }
        return findMember;
    }
}

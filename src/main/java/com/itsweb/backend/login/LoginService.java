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
        return memberRepository.findByUserId(userId);

    }
}

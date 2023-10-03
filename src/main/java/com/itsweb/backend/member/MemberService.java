package com.itsweb.backend.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(Member member) {
        memberRepository.save(member);
    }

    public boolean checkUserIdDuplicated(String userId) {
        return memberRepository.existsByUserId(userId);
    }

    public boolean checkUsernameDuplicated(String username) {
        return memberRepository.existsByUserId(username);
    }


}

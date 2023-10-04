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

    public boolean isDuplicated(Member member) {
        return memberRepository.existsByUsername(member.getUsername()) || memberRepository.existsByUserId(member.getUserId());
    }


}

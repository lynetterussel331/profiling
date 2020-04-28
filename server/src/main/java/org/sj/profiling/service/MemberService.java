package org.sj.profiling.service;

import java.util.List;
import java.util.UUID;
import org.sj.profiling.model.Member;
import org.sj.profiling.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMember() {
        return memberRepository.list();
    }

    public Member createMember(Member member) {
        return memberRepository.create(member);
    }

    public Member getMember(UUID UUID) {
        return memberRepository.get(UUID);
    }

    public Member updateMember(UUID UUID, Member member) {
        return memberRepository.update(UUID, member);
    }

    public void deleteMember(UUID UUID) {
        memberRepository.delete(UUID);
    }

}

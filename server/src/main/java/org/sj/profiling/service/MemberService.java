package org.sj.profiling.service;

import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.sj.profiling.model.Member;
import org.sj.profiling.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
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

    public List<Member> createMembers(List<Member> memberList) {
        for (Member member: memberList) {
            memberRepository.create(member);
        }
        return memberList;
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

    public List<?> getDistinctValues(String columnName) {
        return memberRepository.findDistinctValuesByColumnName(columnName);
    }

}

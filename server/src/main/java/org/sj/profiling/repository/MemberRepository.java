package org.sj.profiling.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.sj.profiling.exception.ResourceNotFoundException;
import org.sj.profiling.model.Member;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary @Repository
public class MemberRepository {

    @Autowired @Lazy
    private MemberJpaRepository jpaRepository;

    public List<Member> list() {
        return jpaRepository.findAll();
    }

    public Member create(Member member) {
        return jpaRepository.save(member);
    }

    public Member get(UUID UUID) {
        return jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("Member"));
    }

    public Member update(UUID UUID, Member member) {
        Member existingData = jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("Member"));

        member.setUUID(UUID);
        member.setAddedDate(existingData.getAddedDate());

        return jpaRepository.save(member);
    }

    public void delete(UUID UUID) {
        Member existingData = jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("Member"));
        jpaRepository.delete(existingData);
    }


}

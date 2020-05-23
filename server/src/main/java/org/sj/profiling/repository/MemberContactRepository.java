package org.sj.profiling.repository;

import java.util.List;
import java.util.UUID;
import org.sj.profiling.exception.ResourceNotFoundException;
import org.sj.profiling.model.MemberContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary @Repository
public class MemberContactRepository {

    @Autowired @Lazy
    private MemberContactJpaRepository jpaRepository;

    public List<MemberContact> list(UUID memberUUID) {
        return jpaRepository.findByMemberUUID(memberUUID);
    }

    public MemberContact create(MemberContact memberContact) {
        return jpaRepository.save(memberContact);
    }

    public MemberContact get(UUID UUID) {
        return jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("MemberContact"));
    }

    public MemberContact update(UUID UUID, MemberContact memberContact) {
        MemberContact existingData = jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("MemberContact"));

        memberContact.setUUID(UUID);
        memberContact.setAddedDate(existingData.getAddedDate());

        return jpaRepository.save(memberContact);
    }

    public void delete(UUID UUID) {
        MemberContact existingData = jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("MemberContact"));
        jpaRepository.delete(existingData);
    }


}

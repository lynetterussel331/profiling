package org.sj.profiling.repository;

import java.util.List;
import java.util.UUID;
import org.sj.profiling.exception.ResourceNotFoundException;
import org.sj.profiling.model.MemberRelative;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary @Repository
public class MemberRelativeRepository {

    @Autowired @Lazy
    private MemberRelativeJpaRepository jpaRepository;

    public List<MemberRelative> list() {
        return jpaRepository.findAll();
    }

    public MemberRelative create(MemberRelative memberRelative) {
        return jpaRepository.save(memberRelative);
    }

    public MemberRelative get(UUID UUID) {
        return jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("MemberRelative"));
    }

    public MemberRelative update(UUID UUID, MemberRelative memberRelative) {
        MemberRelative existingData = jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("MemberRelative"));

        memberRelative.setUUID(UUID);
        memberRelative.setAddedDate(existingData.getAddedDate());

        return jpaRepository.save(memberRelative);
    }

    public void delete(UUID UUID) {
        MemberRelative existingData = jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("MemberRelative"));
        jpaRepository.delete(existingData);
    }


}

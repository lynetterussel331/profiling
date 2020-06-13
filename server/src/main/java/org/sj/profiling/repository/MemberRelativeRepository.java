package org.sj.profiling.repository;

import java.util.List;
import java.util.UUID;
import org.sj.profiling.model.MemberRelative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary @Repository
public class MemberRelativeRepository {

    @Autowired @Lazy
    private MemberRelativeJpaRepository jpaRepository;

    public List<MemberRelative> list(UUID memberUUID) {
        return jpaRepository.findByMemberUUID(memberUUID);
    }

    public MemberRelative create(MemberRelative memberRelative) {
        return jpaRepository.save(memberRelative);
    }

    public MemberRelative get(UUID memberUUID, long collectionId) {
        return jpaRepository.findByMemberUUIDAndCollectionId(memberUUID, collectionId);
    }

    public MemberRelative update(UUID memberUUID, long collectionId, MemberRelative memberRelative) {
        MemberRelative existingData = jpaRepository.findByMemberUUIDAndCollectionId(memberUUID, collectionId);

        memberRelative.setCollectionId(collectionId);
        memberRelative.setAddedDate(existingData.getAddedDate());

        return jpaRepository.save(memberRelative);
    }

    public void delete(UUID memberUUID, long collectionId) {
        MemberRelative existingData = jpaRepository.findByMemberUUIDAndCollectionId(memberUUID, collectionId);
        jpaRepository.delete(existingData);
    }


}

package org.sj.profiling.repository;

import java.util.List;
import java.util.UUID;
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

    public MemberContact get(UUID memberUUID, long collectionId) {
        return jpaRepository.findByMemberUUIDAndCollectionId(memberUUID, collectionId);
    }

    public MemberContact update(UUID memberUUID, long collectionId, MemberContact memberContact) {
        MemberContact existingData = jpaRepository.findByMemberUUIDAndCollectionId(memberUUID, collectionId);

        memberContact.setCollectionId(collectionId);
        memberContact.setAddedDate(existingData.getAddedDate());

        return jpaRepository.save(memberContact);
    }

    public void delete(UUID memberUUID, long collectionId) {
        MemberContact existingData = jpaRepository.findByMemberUUIDAndCollectionId(memberUUID, collectionId);
        jpaRepository.delete(existingData);
    }

}

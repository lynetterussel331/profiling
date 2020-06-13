package org.sj.profiling.repository;

import java.util.List;
import java.util.UUID;
import org.sj.profiling.model.RelativeContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary @Repository
public class RelativeContactRepository {

    @Autowired @Lazy
    private RelativeContactJpaRepository jpaRepository;

    public List<RelativeContact> list(UUID relativeUUID) {
        return jpaRepository.findByRelativeUUID(relativeUUID);
    }

    public RelativeContact create(RelativeContact relativeContact) {
        return jpaRepository.save(relativeContact);
    }

    public RelativeContact get(UUID relativeUUID, long collectionId) {
        return jpaRepository.findByRelativeUUIDAndCollectionId(relativeUUID, collectionId);
    }

    public RelativeContact update(UUID relativeUUID, long collectionId, RelativeContact relativeContact) {
        RelativeContact existingData = jpaRepository.findByRelativeUUIDAndCollectionId(relativeUUID, collectionId);

        relativeContact.setCollectionId(collectionId);
        relativeContact.setAddedDate(existingData.getAddedDate());

        return jpaRepository.save(relativeContact);
    }

    public void delete(UUID relativeUUID, long collectionId) {
        RelativeContact existingData = jpaRepository.findByRelativeUUIDAndCollectionId(relativeUUID, collectionId);
        jpaRepository.delete(existingData);
    }


}

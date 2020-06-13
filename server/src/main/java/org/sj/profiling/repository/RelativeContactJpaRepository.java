package org.sj.profiling.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.sj.profiling.model.RelativeContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelativeContactJpaRepository extends JpaRepository<RelativeContact, UUID> {

    RelativeContact findByRelativeUUIDAndCollectionId(UUID relativeUUID, long collectionId);
    RelativeContact save(RelativeContact relativeContact);
    void delete(RelativeContact relativeContact);

    List<RelativeContact> findByRelativeUUID(UUID relativeUUID);
}

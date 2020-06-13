package org.sj.profiling.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.sj.profiling.model.MemberRelative;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRelativeJpaRepository extends JpaRepository<MemberRelative, UUID> {

    MemberRelative findByMemberUUIDAndCollectionId(UUID memberUUID, long collectionId);
    MemberRelative save(MemberRelative memberRelative);
    void delete(MemberRelative memberRelative);

    List<MemberRelative> findByMemberUUID(UUID memberUUID);

}

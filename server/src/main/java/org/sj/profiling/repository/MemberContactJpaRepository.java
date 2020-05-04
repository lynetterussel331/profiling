package org.sj.profiling.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.sj.profiling.model.MemberContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberContactJpaRepository extends JpaRepository<MemberContact, UUID> {

    Optional<MemberContact> findByUUID(UUID UUID);
    MemberContact save(MemberContact memberContact);
    void delete(MemberContact memberContact);

    List<MemberContact> findByMemberUUID(UUID memberUUID);
}

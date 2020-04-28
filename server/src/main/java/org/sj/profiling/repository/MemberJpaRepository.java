package org.sj.profiling.repository;

import java.util.Optional;
import java.util.UUID;
import org.sj.profiling.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, UUID> {

    Optional<Member> findByUUID(UUID UUID);
    Member save(Member member);
    void delete(Member member);

}

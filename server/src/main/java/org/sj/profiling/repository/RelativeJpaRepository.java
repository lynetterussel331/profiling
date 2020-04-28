package org.sj.profiling.repository;

import java.util.Optional;
import java.util.UUID;
import org.sj.profiling.model.Relative;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelativeJpaRepository extends JpaRepository<Relative, UUID> {

    Optional<Relative> findByUUID(UUID UUID);
    Relative save(Relative relative);
    void delete(Relative relative);

}

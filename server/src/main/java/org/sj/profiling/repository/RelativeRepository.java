package org.sj.profiling.repository;

import java.util.List;
import java.util.UUID;
import org.sj.profiling.exception.ResourceNotFoundException;
import org.sj.profiling.model.Relative;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary @Repository
public class RelativeRepository {

    @Autowired @Lazy
    private RelativeJpaRepository jpaRepository;

    public List<Relative> list() {
        return jpaRepository.findAll();
    }

    public Relative create(Relative relative) {
        return jpaRepository.save(relative);
    }

    public Relative get(UUID UUID) {
        return jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("Relative"));
    }

    public Relative update(UUID UUID, Relative relative) {
        Relative existingData = jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("Relative"));

        relative.setUUID(UUID);
        relative.setAddedDate(existingData.getAddedDate());

        return jpaRepository.save(relative);
    }

    public void delete(UUID UUID) {
        Relative existingData = jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("Relative"));
        jpaRepository.delete(existingData);
    }


}

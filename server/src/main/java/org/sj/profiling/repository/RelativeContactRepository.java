package org.sj.profiling.repository;

import java.util.List;
import java.util.UUID;
import org.sj.profiling.exception.ResourceNotFoundException;
import org.sj.profiling.model.RelativeContact;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary @Repository
public class RelativeContactRepository {

    @Autowired @Lazy
    private RelativeContactJpaRepository jpaRepository;

    public List<RelativeContact> list() {
        return jpaRepository.findAll();
    }

    public RelativeContact create(RelativeContact relativeContact) {
        return jpaRepository.save(relativeContact);
    }

    public RelativeContact get(UUID UUID) {
        return jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("RelativeContact"));
    }

    public RelativeContact update(UUID UUID, RelativeContact relativeContact) {
        RelativeContact existingData = jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("RelativeContact"));

        relativeContact.setUUID(UUID);
        relativeContact.setAddedDate(existingData.getAddedDate());

        return jpaRepository.save(relativeContact);
    }

    public void delete(UUID UUID) {
        RelativeContact existingData = jpaRepository.findByUUID(UUID)
            .orElseThrow(() -> new ResourceNotFoundException("RelativeContact"));
        jpaRepository.delete(existingData);
    }


}

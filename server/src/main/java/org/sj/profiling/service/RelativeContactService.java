package org.sj.profiling.service;

import java.util.List;
import java.util.UUID;
import org.sj.profiling.model.RelativeContact;
import org.sj.profiling.repository.RelativeContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelativeContactService {

    @Autowired
    private RelativeContactRepository relativeContactRepository;

    public List<RelativeContact> getAllRelativeContact(UUID relativeUUID) {
        return relativeContactRepository.list(relativeUUID);
    }

    public RelativeContact createRelativeContact(RelativeContact relativeContact) {
        return relativeContactRepository.create(relativeContact);
    }

    public RelativeContact getRelativeContact(UUID UUID) {
        return relativeContactRepository.get(UUID);
    }

    public RelativeContact updateRelativeContact(UUID UUID, RelativeContact relativeContact) {
        return relativeContactRepository.update(UUID, relativeContact);
    }

    public void deleteRelativeContact(UUID UUID) {
        relativeContactRepository.delete(UUID);
    }

}

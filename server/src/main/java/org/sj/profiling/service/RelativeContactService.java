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

    public RelativeContact getRelativeContact(UUID relativeUUID, long collectionId) {
        return relativeContactRepository.get(relativeUUID, collectionId);
    }

    public RelativeContact updateRelativeContact(UUID relativeUUID, long collectionId, RelativeContact relativeContact) {
        return relativeContactRepository.update(relativeUUID, collectionId, relativeContact);
    }

    public void deleteRelativeContact(UUID relativeUUID, long collectionId) {
        relativeContactRepository.delete(relativeUUID, collectionId);
    }

}

package org.sj.profiling.service;

import java.util.List;
import java.util.UUID;
import org.sj.profiling.model.MemberContact;
import org.sj.profiling.repository.MemberContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberContactService {

    @Autowired
    private MemberContactRepository memberContactRepository;

    public List<MemberContact> getAllMemberContact(UUID memberUUID) {
        return memberContactRepository.list(memberUUID);
    }

    public MemberContact createMemberContact(MemberContact memberContact) {
        return memberContactRepository.create(memberContact);
    }

    public MemberContact getMemberContact(UUID memberUUID, long collectionId) {
        return memberContactRepository.get(memberUUID, collectionId);
    }

    public MemberContact updateMemberContact(UUID memberUUID, long collectionId, MemberContact memberContact) {
        return memberContactRepository.update(memberUUID, collectionId, memberContact);
    }

    public void deleteMemberContact(UUID memberUUID, long collectionId) {
        memberContactRepository.delete(memberUUID, collectionId);
    }

}

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

    public List<MemberContact> getAllMemberContact() {
        return memberContactRepository.list();
    }

    public MemberContact createMemberContact(MemberContact memberContact) {
        return memberContactRepository.create(memberContact);
    }

    public MemberContact getMemberContact(UUID UUID) {
        return memberContactRepository.get(UUID);
    }

    public MemberContact updateMemberContact(UUID UUID, MemberContact memberContact) {
        return memberContactRepository.update(UUID, memberContact);
    }

    public void deleteMemberContact(UUID UUID) {
        memberContactRepository.delete(UUID);
    }

}

package org.sj.profiling.service;

import java.util.List;
import java.util.UUID;
import org.sj.profiling.model.MemberRelative;
import org.sj.profiling.repository.MemberRelativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberRelativeService {

    @Autowired
    private MemberRelativeRepository memberRelativeRepository;

    public List<MemberRelative> getAllMemberRelative(UUID memberUUID) {
        return memberRelativeRepository.list(memberUUID);
    }

    public MemberRelative createMemberRelative(MemberRelative memberRelative) {
        return memberRelativeRepository.create(memberRelative);
    }

    public MemberRelative getMemberRelative(UUID memberUUID, long collectionId) {
        return memberRelativeRepository.get(memberUUID, collectionId);
    }

    public MemberRelative updateMemberRelative(UUID memberUUID, long collectionId, MemberRelative memberRelative) {
        return memberRelativeRepository.update(memberUUID, collectionId, memberRelative);
    }

    public void deleteMemberRelative(UUID memberUUID, long collectionId) {
        memberRelativeRepository.delete(memberUUID, collectionId);
    }

}

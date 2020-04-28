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

    public List<MemberRelative> getAllMemberRelative() {
        return memberRelativeRepository.list();
    }

    public MemberRelative createMemberRelative(MemberRelative memberRelative) {
        return memberRelativeRepository.create(memberRelative);
    }

    public MemberRelative getMemberRelative(UUID UUID) {
        return memberRelativeRepository.get(UUID);
    }

    public MemberRelative updateMemberRelative(UUID UUID, MemberRelative memberRelative) {
        return memberRelativeRepository.update(UUID, memberRelative);
    }

    public void deleteMemberRelative(UUID UUID) {
        memberRelativeRepository.delete(UUID);
    }

}

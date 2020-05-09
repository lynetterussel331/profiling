package org.sj.profiling.controller;

import java.util.List;
import java.util.UUID;
import org.sj.profiling.model.MemberRelative;
import org.sj.profiling.service.MemberRelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class MemberRelativeController {

    @Autowired
    private MemberRelativeService memberRelativeService;

    @RequestMapping(value = "memberRelatives/{memberUUID}", method = RequestMethod.GET)
    public List<MemberRelative> list(@PathVariable UUID memberUUID) {
        return memberRelativeService.getAllMemberRelative(memberUUID);
    }

    @RequestMapping(value = "memberRelatives", method = RequestMethod.POST)
    public MemberRelative create(@RequestBody MemberRelative memberRelative) {
        return memberRelativeService.createMemberRelative(memberRelative);
    }

//    @RequestMapping(value = "memberRelatives/{UUID}", method = RequestMethod.GET)
//    public MemberRelative get(@PathVariable UUID UUID) {
//        return memberRelativeService.getMemberRelative(UUID);
//    }

    @RequestMapping(value = "memberRelatives/{UUID}", method = RequestMethod.PUT)
    public MemberRelative update(@PathVariable UUID UUID, @RequestBody MemberRelative memberRelative) {
        return memberRelativeService.updateMemberRelative(UUID, memberRelative);
    }

    @RequestMapping(value = "memberRelatives/{UUID}", method = RequestMethod.DELETE)
    public void delete(@PathVariable UUID UUID) {
        memberRelativeService.deleteMemberRelative(UUID);
    }

}

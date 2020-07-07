package org.sj.profiling.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.sj.profiling.model.Element;
import org.sj.profiling.model.MemberRelative;
import org.sj.profiling.service.MemberRelativeService;
import org.sj.profiling.utils.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/")
public class MemberRelativeController {

    @Autowired
    private MemberRelativeService memberRelativeService;
    private final static String ITEMNAME = "MemberRelative";
    private final static String PATH = "memberRelatives";

    @RequestMapping(value = "memberRelatives/{memberUUID}", method = RequestMethod.GET)
    public List<MemberRelative> list(@PathVariable UUID memberUUID) {
        return memberRelativeService.getAllMemberRelative(memberUUID);
    }

    @RequestMapping(value = "memberRelatives/{memberUUID}/{collectionId}", method = RequestMethod.GET)
    public MemberRelative get(@PathVariable UUID memberUUID, @PathVariable long collectionId) {
        return memberRelativeService.getMemberRelative(memberUUID, collectionId);
    }

    @RequestMapping(value = "memberRelatives/{memberUUID}", method = RequestMethod.POST)
    public MemberRelative create(@PathVariable UUID memberUUID, @RequestBody MemberRelative memberRelative) {
        memberRelative.setMemberUUID(memberUUID);
        return memberRelativeService.createMemberRelative(memberRelative);
    }

    @RequestMapping(value = "memberRelatives/{memberUUID}/{collectionId}", method = RequestMethod.PUT)
    public MemberRelative update(@PathVariable UUID memberUUID, @PathVariable long collectionId, @RequestBody MemberRelative memberRelative) {
        return memberRelativeService.updateMemberRelative(memberUUID, collectionId, memberRelative);
    }

    @RequestMapping(value = "memberRelatives/{memberUUID}/{collectionId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable UUID memberUUID, @PathVariable long collectionId) {
        memberRelativeService.deleteMemberRelative(memberUUID, collectionId);
    }

    @RequestMapping(value = PATH + "/form", method = RequestMethod.GET, produces = "application/json")
    public String getFormModel() throws IOException, URISyntaxException {
        return FormUtils.getFormModelJSON(ITEMNAME);
    }

}

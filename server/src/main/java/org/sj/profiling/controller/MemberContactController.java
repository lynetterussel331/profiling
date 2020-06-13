package org.sj.profiling.controller;

import java.util.List;
import java.util.UUID;
import org.sj.profiling.model.MemberContact;
import org.sj.profiling.service.MemberContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class MemberContactController {

    @Autowired
    private MemberContactService memberContactService;

    @RequestMapping(value = "memberContacts/{memberUUID}", method = RequestMethod.GET)
    public List<MemberContact> list(@PathVariable UUID memberUUID) {
        return memberContactService.getAllMemberContact(memberUUID);
    }

    @RequestMapping(value = "memberContacts/{memberUUID}/{collectionId}", method = RequestMethod.GET)
    public MemberContact get(@PathVariable UUID memberUUID, @PathVariable long collectionId) {
        return memberContactService.getMemberContact(memberUUID, collectionId);
    }

    @RequestMapping(value = "memberContacts/{memberUUID}", method = RequestMethod.POST)
    public MemberContact create(@PathVariable UUID memberUUID, @RequestBody MemberContact memberContact) {
        memberContact.setMemberUUID(memberUUID);
        return memberContactService.createMemberContact(memberContact);
    }

    @RequestMapping(value = "memberContacts/{memberUUID}/{collectionId}", method = RequestMethod.PUT)
    public MemberContact update(@PathVariable UUID memberUUID, @PathVariable long collectionId, @RequestBody MemberContact memberContact) {
        return memberContactService.updateMemberContact(memberUUID, collectionId, memberContact);
    }

    @RequestMapping(value = "memberContacts/{memberUUID}/{collectionId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable UUID memberUUID, @PathVariable long collectionId) {
        memberContactService.deleteMemberContact(memberUUID, collectionId);
    }

}

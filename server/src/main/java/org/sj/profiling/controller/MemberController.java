package org.sj.profiling.controller;

import java.util.*;
import org.sj.profiling.model.Member;
import org.sj.profiling.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "members", method = RequestMethod.GET)
    public List<Member> list() {
        return memberService.getAllMember();
    }

    @RequestMapping(value = "members", method = RequestMethod.POST)
    public Member create(@RequestBody Member member) {
        return memberService.createMember(member);
    }

    @RequestMapping(value = "members/{UUID}", method = RequestMethod.GET)
    public Member get(@PathVariable UUID UUID) {
        return memberService.getMember(UUID);
    }

    @RequestMapping(value = "members/{UUID}", method = RequestMethod.PUT)
    public Member update(@PathVariable UUID UUID, @RequestBody Member member) {
        return memberService.updateMember(UUID, member);
    }

    @RequestMapping(value = "members/{UUID}", method = RequestMethod.DELETE)
    public void delete(@PathVariable UUID UUID) {
        memberService.deleteMember(UUID);
    }

}

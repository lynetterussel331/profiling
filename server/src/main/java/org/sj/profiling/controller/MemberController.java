package org.sj.profiling.controller;

import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.sj.profiling.model.Member;
import org.sj.profiling.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "members", method = RequestMethod.GET)
    public List<Member> list() {
        List<Member> memberList = memberService.getAllMember();
        log.info(memberList.toString());
        return memberList;
    }

//    @RequestMapping(value = "members", method = RequestMethod.POST)
//    public Member create(@RequestBody Member member) {
//        Member member = memberService.createMember(member);
//        LogUtils.print(member);
//        return member;
//    }

    @RequestMapping(value = "members", method = RequestMethod.POST)
    public List<Member> createList(@RequestBody List<Member> memberList) {
        List<Member> createdMembers = memberService.createMembers(memberList);
        log.info(createdMembers.toString());
        return createdMembers;
    }

    @RequestMapping(value = "members/{UUID}", method = RequestMethod.GET)
    public Member get(@PathVariable UUID UUID) {
        Member member = memberService.getMember(UUID);
        log.info(member.toString());
        return member;
    }

    @RequestMapping(value = "members/{UUID}", method = RequestMethod.PUT)
    public Member update(@PathVariable UUID UUID, @RequestBody Member member) {
        return memberService.updateMember(UUID, member);
    }

    @RequestMapping(value = "members/{UUID}", method = RequestMethod.DELETE)
    public void delete(@PathVariable UUID UUID) {
        memberService.deleteMember(UUID);
    }

    @RequestMapping(value = "members/distinct", method = RequestMethod.GET)
    public List<?> getDistinctValues(@RequestParam String column) {
        List<?> distinctValues = memberService.getDistinctValues(column);
        log.info("distinctValues: " + distinctValues);
        return distinctValues;
    }

}

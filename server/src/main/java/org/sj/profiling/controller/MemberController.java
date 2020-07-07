package org.sj.profiling.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.sj.profiling.model.Member;
import org.sj.profiling.service.MemberService;
import org.sj.profiling.utils.FormUtils;
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
    private final static String ITEMNAME = "Member";
    private final static String PATH = "members";

    @RequestMapping(value = PATH, method = RequestMethod.GET)
    public List<Member> list() {
        List<Member> memberList = memberService.getAllMember();
        log.info(memberList.toString());
        return memberList;
    }

    @RequestMapping(value = PATH, method = RequestMethod.POST)
    public Member create(@RequestBody Member member) {
        Member createdMember = memberService.createMember(member);
        log.info(createdMember.toString());
        return createdMember;
    }

    @RequestMapping(value = PATH + "/{UUID}", method = RequestMethod.GET)
    public Member get(@PathVariable UUID UUID) {
        Member member = memberService.getMember(UUID);
        log.info(member.toString());
        return member;
    }

    @RequestMapping(value = PATH + "/{UUID}", method = RequestMethod.PUT)
    public Member update(@PathVariable UUID UUID, @RequestBody Member member) {
        return memberService.updateMember(UUID, member);
    }

    @RequestMapping(value = PATH + "/{UUID}", method = RequestMethod.DELETE)
    public void delete(@PathVariable UUID UUID) {
        memberService.deleteMember(UUID);
    }

    @RequestMapping(value = PATH + "/distinct", method = RequestMethod.GET)
    public List<?> getDistinctValues(@RequestParam String column) {
        List<?> distinctValues = memberService.getDistinctValues(column);
        log.info("distinctValues: " + distinctValues);
        return distinctValues;
    }

    @RequestMapping(value = PATH + "/distincts", method = RequestMethod.GET)
    public Map<String, List<?>> getDistinctValues(@RequestParam List<String> column) {
        Map<String, List<?>> distinctValuesMap = new HashMap<>();
        column.forEach( it ->
            distinctValuesMap.put(it, memberService.getDistinctValues(it))
        );

        log.info("distinctValuesMap: " + distinctValuesMap);
        return distinctValuesMap;
    }

    @RequestMapping(value = PATH + "/form", method = RequestMethod.GET, produces = "application/json")
    public String getFormModel() throws IOException, URISyntaxException {
        return FormUtils.getFormModelJSON(ITEMNAME);
    }

}

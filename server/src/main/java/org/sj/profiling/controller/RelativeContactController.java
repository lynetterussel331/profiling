package org.sj.profiling.controller;

import java.util.List;
import java.util.UUID;
import org.sj.profiling.model.RelativeContact;
import org.sj.profiling.service.RelativeContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class RelativeContactController {

    @Autowired
    private RelativeContactService relativeContactService;

    @RequestMapping(value = "relativeContacts/{relativeUUID}", method = RequestMethod.GET)
    public List<RelativeContact> list(@PathVariable UUID relativeUUID) {
        return relativeContactService.getAllRelativeContact(relativeUUID);
    }

    @RequestMapping(value = "relativeContacts", method = RequestMethod.POST)
    public RelativeContact create(@RequestBody RelativeContact relativeContact) {
        return relativeContactService.createRelativeContact(relativeContact);
    }

//    @RequestMapping(value = "relativeContacts/{UUID}", method = RequestMethod.PUT)
//    public RelativeContact update(@PathVariable UUID UUID, @RequestBody RelativeContact relativeContact) {
//        return relativeContactService.updateRelativeContact(UUID, relativeContact);
//    }

    @RequestMapping(value = "relativeContacts/{UUID}", method = RequestMethod.DELETE)
    public void delete(@PathVariable UUID UUID) {
        relativeContactService.deleteRelativeContact(UUID);
    }

}

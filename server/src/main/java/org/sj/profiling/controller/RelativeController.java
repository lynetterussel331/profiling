package org.sj.profiling.controller;

import java.util.List;
import java.util.UUID;
import org.sj.profiling.model.Relative;
import org.sj.profiling.service.RelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class RelativeController {

    @Autowired
    private RelativeService relativeService;

    @RequestMapping(value = "relatives", method = RequestMethod.GET)
    public List<Relative> list() {
        return relativeService.getAllRelative();
    }

//    @RequestMapping(value = "relatives", method = RequestMethod.POST)
//    public Relative create(@RequestBody Relative relative) {
//        return relativeService.createRelative(relative);
//    }

    @RequestMapping(value = "relatives", method = RequestMethod.POST)
    public List<Relative> createList(@RequestBody List<Relative> relativeList) {
        return relativeService.createRelatives(relativeList);
    }

    @RequestMapping(value = "relatives/{UUID}", method = RequestMethod.GET)
    public Relative get(@PathVariable UUID UUID) {
        return relativeService.getRelative(UUID);
    }

    @RequestMapping(value = "relatives/{UUID}", method = RequestMethod.PUT)
    public Relative update(@PathVariable UUID UUID, @RequestBody Relative relative) {
        return relativeService.updateRelative(UUID, relative);
    }

    @RequestMapping(value = "relatives/{UUID}", method = RequestMethod.DELETE)
    public void delete(@PathVariable UUID UUID) {
        relativeService.deleteRelative(UUID);
    }

}

package org.sj.profiling.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.sj.profiling.model.Element;
import org.sj.profiling.model.Relative;
import org.sj.profiling.service.RelativeService;
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
public class RelativeController {

    @Autowired
    private RelativeService relativeService;
    private final static String ITEMNAME = "Relative";
    private final static String PATH = "relatives";

    @RequestMapping(value = "relatives", method = RequestMethod.GET)
    public List<Relative> list() {
        List<Relative> relativeList = relativeService.getAllRelative();
        log.info(relativeList.toString());
        return relativeList;
    }

    @RequestMapping(value = "relatives", method = RequestMethod.POST)
    public Relative create(@RequestBody Relative relative) {
        Relative createdRelative = relativeService.createRelative(relative);
        log.info(createdRelative.toString());
        return createdRelative;
    }

    @RequestMapping(value = "relatives/{UUID}", method = RequestMethod.GET)
    public Relative get(@PathVariable UUID UUID) {
        Relative relative = relativeService.getRelative(UUID);
        log.info(relative.toString());
        return relative;
    }

    @RequestMapping(value = "relatives/{UUID}", method = RequestMethod.PUT)
    public Relative update(@PathVariable UUID UUID, @RequestBody Relative relative) {
        return relativeService.updateRelative(UUID, relative);
    }

    @RequestMapping(value = "relatives/{UUID}", method = RequestMethod.DELETE)
    public void delete(@PathVariable UUID UUID) {
        relativeService.deleteRelative(UUID);
    }

    @RequestMapping(value = "relatives/distinct", method = RequestMethod.GET)
    public List<?> getDistinctValues(@RequestParam String column) {
        List<?> distinctValues = relativeService.getDistinctValues(column);
        log.info("distinctValues: " + distinctValues);
        return distinctValues;
    }

    @RequestMapping(value = "relatives/distincts", method = RequestMethod.GET)
    public Map<String, List<?>> getDistinctValues(@RequestParam List<String> column) {
        Map<String, List<?>> distinctValuesMap = new HashMap<>();
        column.forEach( it ->
            distinctValuesMap.put(it, relativeService.getDistinctValues(it))
        );

        log.info("distinctValuesMap: " + distinctValuesMap);
        return distinctValuesMap;
    }

    @RequestMapping(value = PATH + "/form", method = RequestMethod.GET, produces = "application/json")
    public String getFormModel() throws IOException, URISyntaxException {
        return FormUtils.getFormModelJSON(ITEMNAME);
    }

}

package org.sj.profiling.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.sj.profiling.model.Relative;
import org.sj.profiling.repository.RelativeRepository;
import org.sj.profiling.utils.StringUtils.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RelativeService {

    @Autowired
    private RelativeRepository relativeRepository;

    public List<Relative> getAllRelative() {
        return relativeRepository.list();
    }

    public Relative createRelative(Relative relative) {
        return relativeRepository.create(relative);
    }

    public List<Relative> createRelatives(List<Relative> relativeList) {
        for (Relative relative: relativeList) {
            relativeRepository.create(relative);
        }
        return relativeList;
    }

    public Relative getRelative(UUID UUID) {
        return relativeRepository.get(UUID);
    }

    public Relative updateRelative(UUID UUID, Relative relative) {
        return relativeRepository.update(UUID, relative);
    }

    public void deleteRelative(UUID UUID) {
        relativeRepository.delete(UUID);
    }

    public List<?> getDistinctValues(String columnName) {
        List<Filter> formattedList = new ArrayList<>();
        List<?> list = relativeRepository.findDistinctValuesByColumnName(columnName);
        list.forEach( it -> {
            Filter filter = new Filter(it, it);
            formattedList.add(filter);
        });
        return formattedList;
    }

}

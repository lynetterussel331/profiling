package org.sj.profiling.service;

import java.util.List;
import java.util.UUID;
import org.sj.profiling.model.Relative;
import org.sj.profiling.repository.RelativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return relativeRepository.findDistinctValuesByColumnName(columnName);
    }

}

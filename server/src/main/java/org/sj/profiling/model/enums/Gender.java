package org.sj.profiling.model.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Gender {

    FEMALE("FEMALE"),
    MALE("MALE");

    private String label;

    private Gender(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public List<String> getList() {
        return new ArrayList<>(
            Arrays.asList( FEMALE.getLabel(), MALE.getLabel() )
        );
    }
}

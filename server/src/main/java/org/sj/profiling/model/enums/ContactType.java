package org.sj.profiling.model.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ContactType {

    PHONE("Phone"),
    EMAIL("Email");

    private String label;

    private ContactType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public List<String> getContactTypeList() {
        return new ArrayList<>(
            Arrays.asList( PHONE.getLabel(), EMAIL.getLabel() )
        );
    }

}

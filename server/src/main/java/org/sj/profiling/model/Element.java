package org.sj.profiling.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Element {

    private String type;
    private String id;
    private String label;
    private int maxLength;
    private String placeholder;
    private boolean required;

}

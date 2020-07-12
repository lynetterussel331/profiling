package org.sj.profiling.model;

import java.util.List;
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
    private List<Option> options;

    @Getter @Setter @ToString
    public static class Option {
        String label;
        String value;

        public Option(String label, String value) {
            this.label = label;
            this.value = value;
        }
    }
}

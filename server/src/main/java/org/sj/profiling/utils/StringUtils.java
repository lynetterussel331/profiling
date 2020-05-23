package org.sj.profiling.utils;

import com.google.common.base.CaseFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class StringUtils {

    @Getter @Setter @ToString
    public static class Filter {
        String label;
        String value;

        public Filter(Object label, Object value) {
            this.label = label.toString();
            this.value = value.toString();
        }
    }

    public static String toDBColumnCase(String column) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, column);
    }

}

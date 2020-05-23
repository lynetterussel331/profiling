package org.sj.profiling.utils;

import com.google.common.base.CaseFormat;

public class StringUtils {

    public static String toDBColumnCase(String column) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, column);
    }

}

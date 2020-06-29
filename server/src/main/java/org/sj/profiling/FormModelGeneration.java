package org.sj.profiling;

import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.sj.profiling.model.Element;
import org.sj.profiling.utils.FormUtils;

@Slf4j
public class FormModelGeneration {

    private final static String CLASSPATH = "org.sj.profiling.model.";
    private static List<String> items = new ArrayList<>
        (Arrays.asList("Member", "Relative", "MemberContact", "MemberRelative", "RelativeContact"));

    public static void generateFormModels() {
        for (String item : items) {
            try {
                Class c = Class.forName(CLASSPATH + item);
                List<Element> formModel = FormUtils.getFormModel(c);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }
    }

}

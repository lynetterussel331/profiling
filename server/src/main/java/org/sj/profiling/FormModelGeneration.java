package org.sj.profiling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                createFormModelFile(item, convertToJson(formModel));
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }
    }

    private static String convertToJson(Object formModel) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(formModel);
    }

    private static void createFormModelFile(String item, String fileContent) throws IOException {
        String resourcePath = FormModelGeneration.class.getClassLoader().getResource(".").getPath() + "form-models/";
        File dir = new File(resourcePath);
        if (!dir.exists()) dir.mkdir();

        String fileName = resourcePath + item + ".json";
        File file = new File(fileName);
        if (file.createNewFile()) {
            log.info("File is created!");
            writeIntoFile(fileName, fileContent);
        } else {
            log.info("File already exists.");
        }
    }

    private static void writeIntoFile(String fileName, String fileContent) throws IOException {
        log.info("Writing into file {}...", fileName);
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(fileContent);
        printWriter.close();
    }

}

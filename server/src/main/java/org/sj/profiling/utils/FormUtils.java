package org.sj.profiling.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.sj.profiling.model.Element;

@Slf4j
public class FormUtils {

    @Getter @Setter @ToString
    public static class Filter {
        String label;
        String value;

        public Filter(Object label, Object value) {
            this.label = label.toString();
            this.value = value.toString();
        }
    }

    public static String getElementType(String type) {
        switch (type) {
            case "LocalDate":
                return "DATEPICKER";
            case "boolean":
                return "SWITCH";
            default:
                return "INPUT";
        }
    }

    public static List<Element> getFormModel(Class classValue) {
        Field[] fields = classValue.getDeclaredFields();
        List<Element> formModel = new ArrayList<>();
        for (Field field: fields) {
            Element element = new Element();
            setAttributesToElement(element, field);
            formModel.add(element);
        }
        return formModel;
    }

    private static void setAttributesToElement(Element element, Field field) {
        String elementType = FormUtils.getElementType(field.getType().getSimpleName());
        String label = StringUtils.capitalize(StringUtils.join(
            StringUtils.splitByCharacterTypeCamelCase(field.getName()), ' '));
        element.setType(elementType);
        element.setId(field.getName());
        element.setLabel(label);
        element.setMaxLength(50);
        element.setPlaceholder(label);
        setColumnAttributeToElement(element, field);
    }

    private static void setColumnAttributeToElement(Element element, Field field) {
        Column column = field.getAnnotation(Column.class);
        boolean isRequired;
        if (column == null) {
            JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
            isRequired = !joinColumn.nullable();
        } else {
            isRequired = !column.nullable();
        }
        element.setRequired(isRequired);
    }

    public static String getFormModelJSON(String itemName) throws URISyntaxException, IOException {
        URI uri = ClassLoader.getSystemResource("form-models/" +  itemName + ".json").toURI();
        Path filePath = Paths.get(uri);
        return new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
    }

}

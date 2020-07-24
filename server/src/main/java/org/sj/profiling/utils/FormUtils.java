package org.sj.profiling.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.sj.profiling.model.Element;
import org.sj.profiling.model.Element.Option;

@Slf4j
public class FormUtils {

    private final static String CLASSPATH = "org.sj.profiling.model.enums.";

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
        List<String> nonGenerateableTypes = new ArrayList<>(Arrays.asList("java.util.UUID"));
        List<String> nonGenerateableIds = new ArrayList<>(Arrays.asList("addedDate", "modifiedDate", "collectionId"));
        for (Field field: fields) {
            if (!nonGenerateableTypes.contains(field.getType().getTypeName())
                    && !nonGenerateableIds.contains(field.getName())) {
                Element element = new Element();
                setAttributesToElement(element, field);
                formModel.add(element);
            }
        }
        return formModel;
    }

    private static <E extends Enum> E[] getEnumValues(Class<E> enumClass) throws NoSuchFieldException, IllegalAccessException {
        Field f = enumClass.getDeclaredField("$VALUES");
        f.setAccessible(true);
        Object o = f.get(null);
        return (E[]) o;
    }

    private static void setAttributesToElement(Element element, Field field) {
        String id = field.getName();
        String elementType = getElementType(id, field, element);
        String label = StringUtils.capitalize(StringUtils.join(
            StringUtils.splitByCharacterTypeCamelCase(id), ' '));
        element.setId(id);
        element.setType(elementType);
        element.setLabel(label);
        element.setMaxLength(50);
        element.setPlaceholder(label);
        setColumnAttributeToElement(element, field);
    }

    private static String getElementType(String id, Field field, Element element) {
        try {
            Class enumClass = Class.forName(CLASSPATH + id.substring(0, 1).toUpperCase() + id.substring(1));
            Enum[] enumValues = getEnumValues(enumClass);
            List<Option> options = new ArrayList<>();
            options.add(new Option("", ""));
            for (Enum value: enumValues) {
                options.add(new Option(value.name(), value.name()));
            }
            element.setOptions(options);
            return "SELECT";
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            log.info("Class not found: {}", id);
            return FormUtils.getElementType(field.getType().getSimpleName());
        }
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

    public static String getFormModelJSON(String item) throws IOException {
        Path fileName = Paths.get("./src/main/resources/form-models/" + item + ".json");
        URI uri = fileName.toUri();
        Path filePath = Paths.get(uri);
        return new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
    }

}

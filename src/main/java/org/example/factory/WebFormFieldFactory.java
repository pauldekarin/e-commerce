package org.example.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WebFormFieldFactory {
    public static String createId(String name){
        return name.concat(UUID.randomUUID().toString());
    }

    public static WebFormField createInputField(String name){
        WebFormField field = new WebFormField(name);

        field.setPlaceholder(name);
        field.setId(createId(name));
        field.setType("text");

        field.setTag("input");

        return field;
    }

    public static SelectWebFormField createSelectField(String name){
        SelectWebFormField field = new SelectWebFormField(name);
        field.setId(createId(name));
        return field;
    }

    public static WebFormField createDateField(String name){
        WebFormField field = new WebFormField(name);

        field.setTag("input");
        field.setType("date");
        field.setId(createId(name));

        return field;
    }

    public static WebFormField createCheckboxField(String name){
        WebFormField field = new WebFormField(name);

        field.setTag("input");
        field.setType("checkbox");
        field.setId(createId(name));

        return field;
    }
}

package org.example.factory;

import org.aspectj.apache.bcel.classfile.ClassFormatException;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;

public class WebFormFactory {
    public static WebForm createAddForm(Class<?> clazz){
        return createAddForm(clazz, null);
    }

    public static WebForm createAddForm(Class<?> clazz, Map<String, Consumer<SelectWebFormField>> optionAggregators) throws ClassFormatException{
        WebForm webForm = new WebForm();

        for (Field field : clazz.getDeclaredFields()){
            String fieldName = field.getName();

            WebFormField formField;

            if(field.getType().equals(Boolean.class)){
                formField = WebFormFieldFactory.createCheckboxField(fieldName);
            }else if(field.getType().equals(Date.class)){
                formField = WebFormFieldFactory.createDateField(fieldName);
            }else if(field.getType().equals(Long.class) && fieldName.contains("Id")){
                formField = WebFormFieldFactory.createSelectField(fieldName);

                if(!Objects.isNull(optionAggregators) && optionAggregators.containsKey(fieldName)){
                    optionAggregators.get(fieldName).accept((SelectWebFormField) formField);
                }
            }else{
                formField = WebFormFieldFactory.createInputField(fieldName);
            }

            webForm.addFormField(formField);
        }


        String formName = clazz.getSimpleName().replace("DTO", "");

        webForm.setId(String.format("form%sId", formName));

        return webForm;
    }
}

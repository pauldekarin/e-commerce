package org.example.factory;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

@Accessors(chain = true)
public class WebForm {
    @Getter
    private final List<WebFormField> fields = new LinkedList<>();

    @Getter
    private final Map<String, String> apiUrls = new HashMap<>();

    @Setter
    @Getter
    private String id;

    @Getter
    @Setter
    private String uri;

    private final ReentrantLock fieldLock = new ReentrantLock();
    private final ReentrantLock apiLock = new ReentrantLock();

    public WebForm(){
    }

    public void addFormField(WebFormField field){
        fieldLock.lock();

        try {
            fields.add(field);
        }finally {
            fieldLock.unlock();
        }
    }

    public void addApiUri(String name, String uri){
        apiLock.lock();

        try {
            apiUrls.put(name, uri);
        }finally {
            apiLock.unlock();
        }
    }

    public void removeAttribute(String attributeName){
        removeAttribute(null, attributeName);
    }

    public void removeAttribute(String fieldName, String attributeName){
        fieldLock.lock();

        try {
            fields.forEach(field -> {
                if(fieldName == null || field.getName().equals(fieldName)){
                    field.removeAttribute(attributeName);
                }
            });
        }finally {
            fieldLock.unlock();
        }
    }
}

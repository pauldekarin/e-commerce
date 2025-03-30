package org.example.factory;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
public class WebFormField {
    private Map<String, Object> attributes = new HashMap<>();
    private String tag;
    private String name;
    private String label;
    private String type;
    private String id;
    private String placeholder;

    private static final String ATTRIBUTES_NAME = "attributes";

    private final ReentrantLock mapLock = new ReentrantLock();


    public Map<String, String> test = new HashMap<>(){{
        put("placeholder","test");
    }};
    public WebFormField(String name){
        this.name = name;
        this.label = name;
    }

    public void removeAttribute(String name){
        mapLock.lock();

        try {
            attributes.remove(name);
        }finally {
            mapLock.unlock();
        }
    }

    public void setAttribute(String name, Object value){
        mapLock.lock();

        try {
            if(attributes.containsKey(name)){
                attributes.replace(name, value);
            }else{
                attributes.put(name, value);
            }
        }finally {
            mapLock.unlock();
        }
    }

    public Map<String, String> formattedAttributes() {
        return new HashMap<>(){{
            put("placeholder","test");
        }};
//        StringBuilder builder = new StringBuilder();
//        boolean isFirst = true;
//
//        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
//            // Исключаем атрибут "type"
//            if (!entry.getKey().equals("type")) {
//                if (!isFirst) {
//                    builder.append(" ");
//                } else {
//                    isFirst = false;
//                }
//                // Форматируем атрибут как key="value"
//                builder.append(entry.getKey())
//                        .append("=\"")
//                        .append(entry.getValue().toString())
//                        .append("\"");
//            }
//        }
//
//        return builder.toString();
    }
}

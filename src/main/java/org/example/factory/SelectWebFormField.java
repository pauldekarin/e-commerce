package org.example.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class SelectWebFormField extends WebFormField{
    @Getter
    private List<Option> options = new LinkedList<>();

    private final ReentrantLock optionsLock = new ReentrantLock();

    public SelectWebFormField(String name) {
        super(name);

        setTag("select");
    }

    public void putOption(Pair<Long, String>  option){
        this.putOption(option.getFirst().toString(), option.getSecond());
    }

    public void putOption(String value, String name){
        optionsLock.lock();

        try {
            options.add(new Option(value, name));
        }finally {
            optionsLock.unlock();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Option{
        private String value;
        private String name;
    }
}

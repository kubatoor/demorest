package com.demo.rest.model;

import lombok.Data;

@Data
public class Word {
    private String value;
    private int count;

    public Word(String value, int count) {
        this.value = value;
        this.count = count;
    }
}

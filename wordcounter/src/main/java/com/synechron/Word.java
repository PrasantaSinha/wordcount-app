package com.synechron;

import java.util.Locale;

public class Word {
    private String value;
    private Locale language;
    private int count = 0;

    public Word() { }

    public Word(Locale language, String value) {
        this.language = language;
        this.value = value;
        this.count = 1;
    }

    public String getValue() {
        return value;
    }

    public Locale getLanguage() {
        return language;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isValid() {
        for ( char c : value.toCharArray()) {
            if( (c < 'a' && c > 'z') || (c < 'A' && c > 'Z'))
                return false;

        }
        return true;
    }
}

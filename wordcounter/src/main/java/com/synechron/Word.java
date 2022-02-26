package com.synechron;

import java.util.Locale;

public class Word {
    private final String value;
    private final Locale language;

    public Word(Locale language, String value) {
        this.language = language;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Locale getLanguage() {
        return language;
    }

    public boolean isValid() {
        for ( char c : value.toCharArray()) {
            if( (c < 'a' && c > 'z') || (c < 'A' && c > 'Z'))
                return false;

        }
        return true;
    }
}

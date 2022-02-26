package com.synechron;

import java.util.List;
import java.util.ArrayList;

public class WordRepo {

    private final List<Word> words = new ArrayList<>();

    public void add(Word word) {
        words.add(word);
    }

    public List<Word> getAll() {
        return words;
    }
}

package com.synechron;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordRepo {

    private final HashMap<String, List<Word>> words = new HashMap<>();

    public void add(String key, Word word) {
        List<Word> samewords = words.get(key);
        if (samewords == null) {
            samewords = new ArrayList<>();
        }
        samewords = addToTheList(samewords, word);
        words.put(key, samewords);
    }

    private List<Word> addToTheList(List<Word> wordList, Word word) {
        boolean wordExists = false;
        // This can be further improved with implementing a sorted list and a binary search.
        for (Word w : wordList) {
            if( w.getLanguage().equals(word.getLanguage())) {
                w.setCount(w.getCount() + 1);
                wordExists = true;
            }
        }
        if (!wordExists) {
            wordList.add(word);
        }

        return wordList;
    }

    public List<Word> getAll(String key) {
        return words.get(key);
    }
}

package com.synechron;

import java.util.Locale;
import java.util.function.Predicate;

public class WordCounter {

    private WordRepo wordRepo;
    private Translator translator;
    public WordCounter(WordRepo wordRepo, Translator translator) {
        this.wordRepo = wordRepo;
        this.translator = translator;
    }
    public int count(String word) {
        return wordRepo.getAll(word).stream().mapToInt(w -> w.getCount()).sum();
    }

    public void add(Word word) {
        if( !word.isValid()) {
            throw new RuntimeException(word.getValue() + "not a valid word");
        }
        String key = word.getValue();
        if (!word.getLanguage().equals(Locale.ENGLISH)) {
            key = translator.translate(word.getValue());
        }
        wordRepo.add(key, word);
    }


    private Predicate<Word> matching(String word) {
        return w -> {
            if (!w.getLanguage().equals(Locale.ENGLISH)) {
                return translator.translate(w.getValue()).equalsIgnoreCase(word);
            } else {
                return w.getValue().equalsIgnoreCase(word);
            }
        };
    }

}

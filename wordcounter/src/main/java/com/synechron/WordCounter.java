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
        return (int) wordRepo.getAll().stream().filter(matching(word)).count();
    }

    public void add(Word word) {
        if( !word.isValid()) {
            throw new RuntimeException(word.getValue() + "not a valid word");
        }
        wordRepo.add(word);
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

package com.synechron.wordcount.service;

import com.synechron.Word;
import com.synechron.WordCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordCounterService {

    @Autowired
    private WordCounter wordCounter;

    public int count(String word) {
        return wordCounter.count(word);
    }

    public void add(Word word) {
        wordCounter.add(word);
    }
}

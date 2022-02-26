package com.synechron.wordcount;

import com.synechron.Translator;
import com.synechron.WordCounter;
import com.synechron.WordRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public WordRepo wordRepo() {
        return new WordRepo();
    }

    @Bean
    public Translator translator() {
        return word -> {
            throw new RuntimeException("Not Implemented");
        };
    }

    @Bean
    public WordCounter wordCounter(WordRepo wordRepo, Translator translator) {
        return new WordCounter(wordRepo, translator);
    }
}

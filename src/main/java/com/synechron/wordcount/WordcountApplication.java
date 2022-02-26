package com.synechron.wordcount;

import com.synechron.Translator;
import com.synechron.WordRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WordcountApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordcountApplication.class, args);
	}

	@Bean
	public WordRepo getWordRepo() {
		return new WordRepo();
	}

	@Bean
	public Translator getTranslator() {
		return word -> {
			throw new RuntimeException("Not Implemented");
		};
	}

}

package com.synechron;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Locale;

import static org.junit.Assert.assertEquals;


public class WordCounterTest {

    Translator translatorMock = Mockito.mock(Translator.class);

    @Test
    public void shouldReturnACountForWordsInEnglish() {
        Word flower1 = new Word(Locale.ENGLISH, "flower");
        Word flower2 = new Word(Locale.ENGLISH, "flower");

        WordRepo wordRepo = new WordRepo();
        wordRepo.add(flower1);
        wordRepo.add(flower2);

        WordCounter wordCounter = new WordCounter(wordRepo, translatorMock);

        assertEquals("Incorrect word count", 2, wordCounter.count("flower"));

    }

    @Test
    public void shouldReturnACountForWordsOtherThanEnglish() {
        Word flower = new Word(Locale.ENGLISH, "flower");
        Word flor = new Word(Locale.forLanguageTag("es-ES"), "flor");
        Word blume = new Word(Locale.forLanguageTag("de-DE"), "blume");

        Mockito.when(translatorMock.translate("flor")).thenReturn("flower");
        Mockito.when(translatorMock.translate("blume")).thenReturn("flower");

        WordRepo wordRepo = new WordRepo();

        WordCounter wordCounter = new WordCounter(wordRepo, translatorMock);
        wordCounter.add(flower);
        wordCounter.add(flor);
        wordCounter.add(blume);

        assertEquals("Incorrect word count", 3, wordCounter.count("flower"));

    }


    @Test
    public void shouldValidateWords() {
        Word flow1er = new Word(Locale.ENGLISH, "flow1er");
        WordRepo wordRepo = new WordRepo();
        WordCounter wordCounter = new WordCounter(wordRepo, translatorMock);
        try {
            wordCounter.add(flow1er);
        } catch (Exception e) {
            assertEquals("flow1er not a valid word",  e.getMessage());
        }
    }
}
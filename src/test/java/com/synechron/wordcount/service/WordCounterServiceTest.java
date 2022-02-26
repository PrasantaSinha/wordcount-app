package com.synechron.wordcount.service;

import com.synechron.Word;
import com.synechron.WordCounter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WordCounterServiceTest {

    @Mock
    private WordCounter wordCounter;

    @InjectMocks
    private WordCounterService wordCounterService;

    ArgumentCaptor<Word> wordCaptor = ArgumentCaptor.forClass(Word.class);
    @Test
    public void shouldReturnCount() {
        when(wordCounter.count("flower")).thenReturn(3);
        assertEquals(3, wordCounterService.count("flower"));
    }

    @Test
    public void shouldAddWord() {
        wordCounterService.add(new Word(Locale.ENGLISH, "flower"));
        verify(wordCounter).add(wordCaptor.capture());
        assertEquals(Locale.ENGLISH, wordCaptor.getValue().getLanguage());
        assertEquals("flower", wordCaptor.getValue().getValue());
    }
}
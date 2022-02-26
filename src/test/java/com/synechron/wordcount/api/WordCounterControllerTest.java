package com.synechron.wordcount.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.synechron.Translator;
import com.synechron.Word;
import com.synechron.WordRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class WordCounterControllerTest {

    private MockMvc mvc;

    @Mock
    private WordRepo wordRepo;
    @Mock
    private Translator translator;

    @InjectMocks
    private WordCounterController wordCounterController;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(wordCounterController).build();
    }

    @Test
    void shouldReturnCountOnGet() throws Exception {

        List<Word> wordList = Arrays.asList(new Word(Locale.ENGLISH, "flower"),
                new Word(Locale.forLanguageTag("es-ES"), "flor"),
                new Word(Locale.forLanguageTag("de-DE"), "blume"));

        when(wordRepo.getAll()).thenReturn(wordList);
        when(translator.translate("flor")).thenReturn("flower");
        when(translator.translate("blume")).thenReturn("flower");

        mvc.perform(get("/v1/words/flower/count")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"count\":3}"));
    }

    @Test
    void shouldReturnCreatedOnPost() throws Exception {

        mvc.perform(post("/v1/words").content(getJsonString(new Word(Locale.ENGLISH, "flower")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    private String getJsonString(Word word) throws JsonProcessingException {
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(word);
    }
}
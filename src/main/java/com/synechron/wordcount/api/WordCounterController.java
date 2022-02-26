package com.synechron.wordcount.api;

import com.synechron.Translator;
import com.synechron.Word;
import com.synechron.WordCounter;
import com.synechron.WordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/words")
public class WordCounterController {

    @Autowired
    private WordRepo wordRepo;

    @Autowired
    private Translator translator;

    @GetMapping(path = "/{word}/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> count(@PathVariable("word") String word) {

        WordCounter wordCounter = new WordCounter(wordRepo, translator);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new StringBuilder()
                        .append("{\"count\":").append(wordCounter.count(word))
                        .append("}")
                        .toString());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(@RequestBody Word word) {
        WordCounter wordCounter = new WordCounter(wordRepo, translator);
        wordCounter.add(word);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

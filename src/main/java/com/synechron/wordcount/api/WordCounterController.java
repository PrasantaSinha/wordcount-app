package com.synechron.wordcount.api;

import com.synechron.Word;
import com.synechron.wordcount.service.WordCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/words")
public class WordCounterController {

    @Autowired
    private WordCounterService wordCounterService;

    @GetMapping(path = "/{word}/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> count(@PathVariable("word") String word) {

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new StringBuilder()
                        .append("{\"count\":").append(wordCounterService.count(word))
                        .append("}")
                        .toString());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(@RequestBody Word word) {
        wordCounterService.add(word);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

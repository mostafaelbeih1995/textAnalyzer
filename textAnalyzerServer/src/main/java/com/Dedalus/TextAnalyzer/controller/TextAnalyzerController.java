package com.Dedalus.TextAnalyzer.controller;

import com.Dedalus.TextAnalyzer.service.TextAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyze")
public class TextAnalyzerController {

    @Autowired
    private TextAnalyzerService textAnalyzerService;

    @GetMapping("{text}/{type}")
    public String test(@PathVariable String text, @PathVariable String type){
        textAnalyzerService.analyzeText(text, type);
        return "successful";
    }
}

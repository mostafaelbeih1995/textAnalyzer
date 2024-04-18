package com.Dedalus.TextAnalyzer.controller;

import com.Dedalus.TextAnalyzer.exception.InvalidRequestException;
import com.Dedalus.TextAnalyzer.model.payload.AnalyzeTextRequest;
import com.Dedalus.TextAnalyzer.service.TextAnalyzerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/analyze")
public class TextAnalyzerController {

    @Autowired
    private TextAnalyzerService textAnalyzerService;

    @PostMapping
    public Map<Character, Integer>analyzeText(@Valid @RequestBody AnalyzeTextRequest request, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(bindingResult);
        }
        return textAnalyzerService.analyzeText(request.getText(), request.getType());
    }
}

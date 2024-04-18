package com.Dedalus.TextAnalyzer.service;

import com.Dedalus.TextAnalyzer.model.enums.AnalysisMode;
import com.Dedalus.TextAnalyzer.model.enums.ModeValidCharacters;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TextAnalyzerService {
    public Map<Character, Integer> analyzeText(String text, AnalysisMode type){
        Map<Character, Integer> result = new LinkedHashMap<>();
        String charactersToCheck = getRelevantCharacters(type);

        text = text.toLowerCase();
        for(char c: text.toCharArray()){
            if(charactersToCheck.indexOf(c) != -1){
                result.put(c, result.getOrDefault(c, 0) + 1);
            }
        }
        return result;
    }

    private String getRelevantCharacters(AnalysisMode type){
        return switch (type){
            case CONSONANTS -> ModeValidCharacters.CONSONANTS.getValidCharacters();
            default -> ModeValidCharacters.VOWELS.getValidCharacters();
        };
    }
}

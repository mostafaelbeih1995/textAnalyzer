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
        String charactersToCheck = type.getType().equals("Vowels") ? ModeValidCharacters.VOWELS.getValidCharacters() :
                                    type.getType().equals("Consonants") ? ModeValidCharacters.CONSONANTS.getValidCharacters() :
                                            ModeValidCharacters.VOWELS.getValidCharacters();

        text = text.toLowerCase();
        for(char c: text.toCharArray()){
            if(charactersToCheck.indexOf(c) != -1){
                result.put(c, result.getOrDefault(c, 0) + 1);
            }
        }
        return result;
    }
}

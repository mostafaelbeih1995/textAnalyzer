package com.Dedalus.TextAnalyzer;

import com.Dedalus.TextAnalyzer.model.enums.AnalysisMode;
import com.Dedalus.TextAnalyzer.service.TextAnalyzerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TextAnalyzerServiceTest {

    @Autowired
    private TextAnalyzerService textAnalyzerService;

    private final String text1 = "Banana";
    private final String text2 = "!@#$%^&*(";
    private final String text3 = "OEUI";
    @Test
    void TestDemo(){
        assertTrue(true);
    }

    @Test
    void positiveScenarioAllLetters(){
        String text = "Banana";
        AnalysisMode analysisMode = AnalysisMode.ALLLETTERS;
        var result = textAnalyzerService.analyzeText(text, analysisMode);
        assertEquals(1, result.get('b'));
        assertEquals(3, result.get('a'));
        assertEquals(2, result.get('n'));
    }

    @Test
    void positiveScenarioVOWELS(){
        String text = "Banana";
        AnalysisMode analysisMode = AnalysisMode.VOWELS;
        var result = textAnalyzerService.analyzeText(text, analysisMode);
        assertEquals(3, result.get('a'));
    }

    @Test
    void positiveScenarioConsonants(){
        String text = "Banana";
        AnalysisMode analysisMode = AnalysisMode.ALLLETTERS;
        var result = textAnalyzerService.analyzeText(text, analysisMode);
        assertEquals(1, result.get('b'));
        assertEquals(2, result.get('n'));
    }

    @Test
    void allSymbolsText(){
        String text = "!@#$%^&*()";
        AnalysisMode analysisMode = AnalysisMode.ALLLETTERS;
        var result = textAnalyzerService.analyzeText(text, analysisMode);
        assertEquals(0, result.size());
    }

    @Test
    void allVowelsConsonantMode(){
        String text = "AEUOuoeaaiu";
        AnalysisMode analysisMode = AnalysisMode.CONSONANTS;
        var result = textAnalyzerService.analyzeText(text, analysisMode);
        assertEquals(0, result.size());
    }

    @Test
    void allConsonantsVowelMode(){
        String text = "mstflbhsthbst";
        AnalysisMode analysisMode = AnalysisMode.VOWELS;
        var result = textAnalyzerService.analyzeText(text, analysisMode);
        assertEquals(0, result.size());
    }

    @Test
    void emptyText(){
        String text = "";
        AnalysisMode analysisMode = AnalysisMode.VOWELS;
        var result = textAnalyzerService.analyzeText(text, analysisMode);
        assertEquals(0, result.size());
    }



}

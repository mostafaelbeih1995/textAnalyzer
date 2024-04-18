package com.Dedalus.TextAnalyzer.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnalysisMode {

    VOWELS("Vowels"),
    CONSONANTS("Consonants");

    private final String type;
}
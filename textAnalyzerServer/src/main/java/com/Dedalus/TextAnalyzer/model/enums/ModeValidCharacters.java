package com.Dedalus.TextAnalyzer.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModeValidCharacters {

    VOWELS("aeiou"),
    CONSONANTS("bcdfghjklmnpqrstvwxyz");
    private final String validCharacters;
}

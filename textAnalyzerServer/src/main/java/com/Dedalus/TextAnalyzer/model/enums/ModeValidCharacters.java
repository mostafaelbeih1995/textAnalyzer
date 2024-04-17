package com.Dedalus.TextAnalyzer.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModeValidCharacters {

    VOWELS("aeiou"),
    CONSONANTS("bcdfghjklmnpqrstvwxyz"),

    ALLLETTERS("abcdefghijklmnopqrstuvwxyz");
    private final String validCharacters;
}

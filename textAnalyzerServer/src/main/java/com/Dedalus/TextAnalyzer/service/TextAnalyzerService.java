package com.Dedalus.TextAnalyzer.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TextAnalyzerService {

    public void analyzeText(String text, String type) {

        String input = "";
        int numA = 0;
        int numE = 0;
        int numI = 0;
        int numO = 0;
        int numU = 0;
        if (type.equals("vowels")) {
            input = text;
            char[] chars = input.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                System.out.println(chars[i]);
                if (chars[i] == 'a' || chars[i] == 'A')
                    numA++;
                if (chars[i] == 'e' || chars[i] == 'E')
                    numE++;
                if (chars[i] == 'i' || chars[i] == 'I')
                    numI++;
                if (chars[i] == 'o' || chars[i] == 'O')
                    numO++;
                if (chars[i] == 'u' || chars[i] == 'U')
                    numU++;
            }
            System.out.println("Letter 'A' appears " + numA + " times");
            System.out.println("Letter 'E' appears " + numE + " times");
            System.out.println("Letter 'I' appears " + numI + " times");
            System.out.println("Letter 'O' appears " + numO + " times");
            System.out.println("Letter 'U' appears " + numU + " times");
        } else if (type.equals("consonants")) {
            input = text;
            HashMap<String, Integer> consonants = new HashMap<>();
            char[] chars = input.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != 'a'
                        && chars[i] != 'A'
                        && chars[i] != 'e'
                        && chars[i] != 'E'
                        && chars[i] != 'i'
                        && chars[i] != 'I'
                        && chars[i] != 'o'
                        && chars[i] != 'O'
                        && chars[i] != 'u'
                        && chars[i] != 'U'
                ) {
                    String stringCharacter = String.valueOf(chars[i]).toUpperCase();
                    if (consonants.containsKey(stringCharacter)) {
                        Integer num = consonants.get(stringCharacter);
                        num++;
                        consonants.put(stringCharacter, num);
                    } else {
                        consonants.put(stringCharacter, 1);
                    }
                }
            }
            consonants.entrySet().forEach(entrySet -> {
                System.out.println("Letter '" + entrySet.getKey() + "' appears " + entrySet.getValue() + " times");
            });
        }
    }
}

package com.allboard.verification.common.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public final class RandomStringGenerator {
    private final String NUMBERS = "0123456789";
    private final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String SYMBOLS = "~!()*_-[]{}.$^";
    private final SecureRandom random = new SecureRandom();

    private int length;
    private boolean useLetters;
    private boolean useNumbers;
    private boolean useSymbols;

    public RandomStringGenerator length(int length) {
        this.length = length;
        return this;
    }

    public RandomStringGenerator useLetters(boolean useLetters) {
        this.useLetters = useLetters;
        return this;
    }
    public RandomStringGenerator useNumbers(boolean useNumbers) {
        this.useNumbers = useNumbers;
        return this;
    }
    public RandomStringGenerator useSymbols(boolean useSymbols) {
        this.useSymbols = useSymbols;
        return this;
    }

    public String generate() {
        String characters = "";
        characters += this.useLetters ? LETTERS : "";
        characters += this.useNumbers ? NUMBERS : "";
        characters += this.useSymbols ? SYMBOLS : "";

        int characterLength = characters.length();

        StringBuilder sb = new StringBuilder(this.length);

        for(int i = 0; i < this.length; i++){
            sb.append(characters.charAt(random.nextInt(characterLength)));
        }

        this.useLetters = false;
        this.useNumbers = false;
        this.useSymbols = false;
        this.length = 6;

        return sb.toString();
    }
}
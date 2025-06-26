package sms.allBoard.Common.Util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class RandomStringGenerator {
    private final String NUMBERS = "0123456789";
    private final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String SYMBOLS = "~!()*_-[]{}.$^";
    private final SecureRandom random = new SecureRandom();

    public String generate(int length, boolean letters, boolean numbers, boolean symbols) {
        String characters = "";
        characters += letters ? LETTERS : "";
        characters += numbers ? NUMBERS : "";
        characters += symbols ? SYMBOLS : "";

        int characterLength = characters.length();

        StringBuilder sb = new StringBuilder(length);

        for(int i = 0; i < length; i++){
            sb.append(characters.charAt(random.nextInt(characterLength)));
        }

        return sb.toString();
    }
}

package com.allboard.verification.service.impl;

import com.allboard.verification.common.service.CodeGenerator;
import com.allboard.verification.common.util.RandomStringGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class EmailCodeGenerator implements CodeGenerator {
    private final RandomStringGenerator randomStringGenerator;
    @Override
    public String generate() {
        return randomStringGenerator
                .length(6)
                .useLetters(true)
                .useNumbers(true)
                .useSymbols(false)
                .generate();
    }
}

package com.allboard.verification.controller;

import com.allboard.verification.vo.EmailInfo;
import com.allboard.verification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/verification/email`")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    @PostMapping
    public boolean verificationEmail(
            @RequestBody(required = false) EmailInfo info
    ) {
        if(info == null || info.getIdentifier() == null){
            return false;
        }
        log.info(info.getIdentifier());
        log.info(info.getCode());
        boolean isAuthenticated = emailService.authenticate(info);
        return isAuthenticated;
    }
}

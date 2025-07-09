package com.allboard.auth.feign.verification;

import com.allboard.auth.feign.verification.dto.EmailVerificationRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "verification-service")
public interface VerificationClient {
    @PostMapping("/verification/email")
    boolean verify(@RequestBody EmailVerificationRequestDTO requestDTO);
}

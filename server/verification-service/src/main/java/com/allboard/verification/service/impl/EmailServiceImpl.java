package com.allboard.verification.service.impl;

import com.allboard.verification.common.vo.Info;
import com.allboard.verification.common.service.CodeGenerator;
import com.allboard.verification.common.service.CodeSender;
import com.allboard.verification.common.service.InfoVerifier;
import com.allboard.verification.common.service.InfoStore;
import com.allboard.verification.vo.EmailInfo;
import com.allboard.verification.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final CodeGenerator codeGenerator;
    private final CodeSender codeSender;
    private final InfoVerifier infoVerifier;
    private final InfoStore infoStore;

    @Override
    public void sendCode(String email) {
        String code = codeGenerator.generate();
        Info info = new EmailInfo(email, code);

        infoStore.set(info);
        codeSender.send(info);
    }

    @Override
    public boolean authenticate(Info inputInfo) {
        String email = inputInfo.getIdentifier();
        Info isssuedInfo = infoStore.get(email);
        if(isssuedInfo==null || isssuedInfo.getCode()==null){
            return false;
        }

        return infoVerifier.verify(isssuedInfo, inputInfo);
    }
}

package sms.allBoard.Common.Service.Auth.Verification.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sms.allBoard.Common.Service.Auth.Verification.Authenticator.VerificationAuthenticator;
import sms.allBoard.Common.Service.Auth.Verification.Generator.VerificationGenerator;
import sms.allBoard.Common.Service.Auth.Verification.Identifier.Identifier;
import sms.allBoard.Common.Service.Auth.Verification.Info.EmailVerificationInfo;
import sms.allBoard.Common.Service.Auth.Verification.Info.VerificationInfo;
import sms.allBoard.Common.Service.Auth.Verification.Sender.VerificationSender;
import sms.allBoard.Common.Service.Auth.Verification.Store.VerificationStore;
import sms.allBoard.Common.Service.Auth.Verification.VerificationService;


@Service("emailVerificationService")
public final class EmailVerificationService implements VerificationService {
    private final VerificationGenerator generator;
    private final VerificationSender sender;
    private final VerificationAuthenticator authenticator;
    private final VerificationStore store;

    @Autowired
    public EmailVerificationService (
            @Qualifier("emailVerificationGenerator")
            VerificationGenerator generator,

            @Qualifier("emailVerificationSender")
            VerificationSender sender,

            @Qualifier("emailVerificationAuthenticator")
            VerificationAuthenticator authenticator,

            @Qualifier("emailVerificationStore")
            VerificationStore store
    ) {
        this.generator = generator;
        this.sender = sender;
        this.authenticator = authenticator;
        this.store = store;
    }

    @Override
    public void send(Identifier identifier) {
        String code = generator.generate();
        VerificationInfo info = new EmailVerificationInfo(identifier, code);

        store.set(info);
        sender.send(info);
    }

    @Override
    public boolean authenticate(VerificationInfo info) {
        VerificationInfo issuedInfo = store.get();
        if (issuedInfo == null) {
            return false;
        }

        return authenticator.authenticate(issuedInfo, info);
    }
}

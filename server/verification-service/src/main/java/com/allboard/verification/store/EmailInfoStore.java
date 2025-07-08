package com.allboard.verification.store;

import com.allboard.verification.common.vo.Info;
import com.allboard.verification.common.service.InfoStore;
import com.allboard.verification.common.util.RedisUtil;
import com.allboard.verification.vo.EmailInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class EmailInfoStore implements InfoStore {
    private final String KEY_PREFIX = "verification.email.";
    private final RedisUtil redisUtil;
    @Override
    public Info get(String identifier) {
        String email = identifier;
        String code = redisUtil.get(KEY_PREFIX+email);
        Info info = new EmailInfo(identifier, code);
        return info;
    }

    @Override
    public void set(Info info) {
        String email = info.getIdentifier();
        String code = info.getCode();
        redisUtil.set(KEY_PREFIX+email, code);
    }

    @Override
    public void delete(String email ) {
        redisUtil.delete(KEY_PREFIX+email);
    }
}

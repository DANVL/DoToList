package org.doto.service.impl;

import org.doto.config.token.TokenProvider;
import org.doto.entity.User;
import org.doto.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenProvider tokenProvider;

    @Autowired
    public TokenServiceImpl(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public String tokenProvider(User user) {
        return tokenProvider.provideToken(user);
    }
}

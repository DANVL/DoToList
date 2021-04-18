package org.doto.service;

import org.doto.entity.User;

public interface TokenService {
    String tokenProvider(User user);
}

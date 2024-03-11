package com.security.service;

import com.security.dto.request.SaveUser;
import com.security.persistence.entity.User;

import java.util.Optional;

public interface UserService {
    User registrOneCustomer(SaveUser newUser);

    Optional<User> findOneByUsername(String username);
}

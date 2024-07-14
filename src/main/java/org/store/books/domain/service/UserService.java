package org.store.books.domain.service;

import org.store.books.web.dto.SignupRequest;

public interface UserService {
    void signUp(SignupRequest signupDto);
}

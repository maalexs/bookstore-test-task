package org.store.books.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.store.books.infrastructure.exception.DuplicateLoginException;
import org.store.books.persistence.entity.User;
import org.store.books.persistence.repository.UserRepository;
import org.store.books.web.dto.SignupRequest;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasicUserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequest request) {
        String email = request.getEmail();
        Optional<User> existingUser = repository.findByEmail(email);

        if (existingUser.isPresent()) {
            throw new DuplicateLoginException(String.format("User with the email address '%s' already exists.", email));
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getName(), email, hashedPassword);
        repository.save(user);
    }
}

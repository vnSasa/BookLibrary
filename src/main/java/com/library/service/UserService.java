package com.library.service;

import com.library.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    Optional<User> getById(Long id);

    void save(User user);

    void delete(Long id);

    List<User> getAll();
}

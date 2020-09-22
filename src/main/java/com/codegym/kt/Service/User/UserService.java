package com.codegym.kt.Service.User;

import com.codegym.kt.model.User;

public interface UserService {
    Iterable<User> findAll();

    User findById(Long id);

    void save(User province);

    void remove(Long id);
}

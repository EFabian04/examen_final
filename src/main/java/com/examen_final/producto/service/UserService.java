package com.examen_final.producto.service;

import com.examen_final.producto.models.User;
import java.util.List;

public interface UserService {
    User getUserById(Long id);

    List<User> allUsers();

    User createUser(User user);

    User updateUser(Long id, User user);

    String login(User user);
}

package org.doto.service;

import org.doto.entity.Role;
import org.doto.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);

    List<User> findAll();

    User findById(Integer id);

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    void deleteById(Integer id);

    void updateRoleById(Integer id, Role role);
}

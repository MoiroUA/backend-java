package ua.vision.moiro.vision.service;

import ua.vision.moiro.vision.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Integer id);
    User findByEmail(String email);
    User createUser(User entity);
    void updateUserById(User entity, Integer id);
    void deleteUserById(Integer id);
}

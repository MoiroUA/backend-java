package ua.vision.moiro.vision.service;

import ua.vision.moiro.vision.DTO.SignUpDto;
import ua.vision.moiro.vision.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Integer id);
    User findByEmail(String email);
    User registrationUser(SignUpDto signUpDto);
    void updateUserById(User entity, Integer id);
    void deleteUserById(Integer id);
    Boolean existsByEmail(String email);
}

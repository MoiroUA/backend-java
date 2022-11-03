package ua.vision.moiro.vision.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.vision.moiro.vision.model.User;
import ua.vision.moiro.vision.repository.UserRepository;
import ua.vision.moiro.vision.service.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).
                orElseThrow(RuntimeException::new);
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException();
        } else {
            return user;
        }
    }

    @Override
    @Transactional
    public User createUser(User entity) {
        userRepository.save(entity);
        return entity;
    }

    @Override
    @Transactional
    public void updateUserById(User entity, Integer id) {
        User user = userRepository.findById(id).
                orElseThrow(RuntimeException::new);
        user.setName(entity.getName());
        user.setSurname(entity.getSurname());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        userRepository.delete(user);
    }

}

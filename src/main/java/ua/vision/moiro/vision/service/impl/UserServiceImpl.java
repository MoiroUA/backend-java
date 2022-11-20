package ua.vision.moiro.vision.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.vision.moiro.vision.DTO.SignUpDto;
import ua.vision.moiro.vision.exception.EmailNotFound;
import ua.vision.moiro.vision.exception.UserExistsInApp;
import ua.vision.moiro.vision.model.Role;
import ua.vision.moiro.vision.model.TypeBlood;
import ua.vision.moiro.vision.model.User;
import ua.vision.moiro.vision.repository.RoleRepository;
import ua.vision.moiro.vision.repository.TypeBloodRepository;
import ua.vision.moiro.vision.repository.UserRepository;
import ua.vision.moiro.vision.service.UserService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TypeBloodRepository typeBloodRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           TypeBloodRepository typeBloodRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.typeBloodRepository = typeBloodRepository;
        this.passwordEncoder = passwordEncoder;
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
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email not found"));
    }

    @Override
    @Transactional
    public User registrationUser(SignUpDto entity) {
        Boolean existUser =  userRepository.existsByEmail(entity.getEmail());
        if(existUser) {
            throw new UserExistsInApp(entity.getEmail());
        } else {
            Role roleUser = roleRepository.findByName("USER");
            Set<Role> roles = new HashSet<>();
            roles.add(roleUser);

            TypeBlood typeBlood = typeBloodRepository.findById(1).orElseThrow();


            String password = passwordEncoder.encode(entity.getPassword());

            User newUser = new User(entity.getName(),
                    entity.getSurname(),
                    entity.getEmail(),
                    password,
                    roles,
                    typeBlood);

            return userRepository.save(newUser);
        }
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

    @Override
    public Boolean existsByEmail(String email) {
        var exist = userRepository.existsByEmail(email);
        if(exist) {
            return true;
        } else {
            throw new EmailNotFound(email);
        }
    }

}

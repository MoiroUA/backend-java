package ua.vision.moiro.vision.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.vision.moiro.vision.model.User;
import ua.vision.moiro.vision.service.UserService;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public SecurityUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + email + " not found");
        }

        SecurityUser securityUser = SecurityUserFactory.create(user);
        return securityUser;
    }
}
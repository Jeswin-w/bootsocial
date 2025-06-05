package com.saharaj.bootsocial.userprofile.service;

import com.saharaj.bootsocial.userprofile.entity.User;

import com.saharaj.bootsocial.userprofile.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        return userRepository.save(user);
    }
    public String hashPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean matches (Integer userID, String password) {
        Optional<User> currentUser = userRepository.findById(userID);
        User user = currentUser.orElseThrow();

        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  userRepository.findByUserName(username)
                .orElseThrow(() ->  new UsernameNotFoundException("User not found"));

    }
}

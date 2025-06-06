package com.saharaj.bootsocial.userprofile.service;

import com.saharaj.bootsocial.userprofile.entity.AppUser;

import com.saharaj.bootsocial.userprofile.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser addUser(AppUser user) {
        user.setPassword(hashPassword(user.getPassword()));
        return userRepository.save(user);
    }
    public String hashPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean matches (Long userID, String password) {
        Optional<AppUser> currentUser = userRepository.findById(userID);
        AppUser user = currentUser.orElseThrow();

        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser =  userRepository.findByUsername(username)
                .orElseThrow(() ->  new UsernameNotFoundException("User not found"));
        return new User(appUser.getUsername(), appUser.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));

    }
}

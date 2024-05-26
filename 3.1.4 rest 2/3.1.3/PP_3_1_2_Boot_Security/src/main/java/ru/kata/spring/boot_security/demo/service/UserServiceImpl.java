package ru.kata.spring.boot_security.demo.service;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void updateUser (Long id, User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
       if (userRepository.findById(id).isPresent()){
           userRepository.deleteById(id);
       }
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Transactional
    @Override
    public void saveUser( User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}

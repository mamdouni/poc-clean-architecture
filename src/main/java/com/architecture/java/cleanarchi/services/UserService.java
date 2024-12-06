package com.architecture.java.cleanarchi.services;

import com.architecture.java.cleanarchi.entities.UserEntity;
import com.architecture.java.cleanarchi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(Integer id) {
        return userRepository.findById(id);
    }

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}

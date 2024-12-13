package com.architecture.java.cleanarchi.services;

import com.architecture.java.cleanarchi.entities.TaskEntity;
import com.architecture.java.cleanarchi.entities.UserEntity;
import com.architecture.java.cleanarchi.exceptions.ExceptionsMessagesEnum;
import com.architecture.java.cleanarchi.exceptions.ResourceNotFoundException;
import com.architecture.java.cleanarchi.repositories.UserRepository;
import lombok.AllArgsConstructor;
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


    public Optional<TaskEntity> createTask(Integer userId, TaskEntity taskEntity) {

        var user = findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionsMessagesEnum.NOT_FOUND_USER));
        user.addTask(taskEntity);
         return save(user)
                 .getTasks().stream()
                .filter(task -> task.getTitle().equals(taskEntity.getTitle()))
                .findFirst();
    }

    public void removeTask(Integer userId, Integer taskId) {

        var user = findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionsMessagesEnum.NOT_FOUND_USER));
        var task = user.getTasks()
                .stream()
                .filter(t -> t.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionsMessagesEnum.NOT_FOUND_TASK));

        user.removeTask(task);
        save(user);
    }
}

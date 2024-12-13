package com.architecture.java.cleanarchi.services;

import com.architecture.java.cleanarchi.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public void deleteById(Integer id) {
        taskRepository.deleteById(id);
    }
}

package com.architecture.java.hexa.services;

import com.architecture.java.hexa.repositories.TaskRepository;
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

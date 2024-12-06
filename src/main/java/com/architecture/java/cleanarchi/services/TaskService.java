package com.architecture.java.cleanarchi.services;

import com.architecture.java.cleanarchi.entities.TaskEntity;
import com.architecture.java.cleanarchi.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskEntity> findAll() {
        return taskRepository.findAll();
    }

    public Optional<TaskEntity> findById(Integer id) {
        return taskRepository.findById(id);
    }

    public TaskEntity save(TaskEntity task) {
        return taskRepository.save(task);
    }

    public void deleteById(Integer id) {
        taskRepository.deleteById(id);
    }
}

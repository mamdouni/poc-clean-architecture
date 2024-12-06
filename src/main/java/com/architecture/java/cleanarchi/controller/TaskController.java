package com.architecture.java.cleanarchi.controller;

import com.architecture.java.cleanarchi.controller.dtos.TaskDTO;
import com.architecture.java.cleanarchi.controller.dtos.UserDTO;
import com.architecture.java.cleanarchi.controller.mappers.TaskMapper;
import com.architecture.java.cleanarchi.controller.mappers.UserMapper;
import com.architecture.java.cleanarchi.exceptions.ExceptionsMessagesEnum;
import com.architecture.java.cleanarchi.services.TaskService;
import com.architecture.java.cleanarchi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.architecture.java.cleanarchi.exceptions.ResourceNotFoundException;
import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteTask(@PathVariable Integer id) {
        taskService.deleteById(id);
    }
}

package com.architecture.java.hexa.controller;

import com.architecture.java.hexa.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

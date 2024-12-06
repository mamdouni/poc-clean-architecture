package com.architecture.java.cleanarchi.entities;

import com.architecture.java.cleanarchi.controller.dtos.TaskDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TaskEntity> tasks;

    public void addTask(TaskEntity taskEntity) {
        this.tasks.add(taskEntity);
        taskEntity.setUser(this);
    }
}

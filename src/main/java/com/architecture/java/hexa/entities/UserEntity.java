package com.architecture.java.hexa.entities;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TaskEntity> tasks;

    public void addTask(TaskEntity taskEntity) {
        this.tasks.add(taskEntity);
        taskEntity.setUser(this);
    }

    public void removeTask(TaskEntity taskEntity) {
        this.tasks.remove(taskEntity);
        taskEntity.setUser(null);
    }
}

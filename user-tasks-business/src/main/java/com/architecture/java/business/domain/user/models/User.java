package com.architecture.java.business.domain.user.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class User {

    private Integer id;

    private String username;

    private String email;

    private Set<Task> tasks;

    public void addTask(Task taskEntity) {
        this.tasks.add(taskEntity);
    }

    public void removeTask(Task taskEntity) {
        this.tasks.remove(taskEntity);
    }
}

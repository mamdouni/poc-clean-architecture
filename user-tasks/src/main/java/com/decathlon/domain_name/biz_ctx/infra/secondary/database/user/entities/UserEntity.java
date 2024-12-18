package com.decathlon.domain_name.biz_ctx.infra.secondary.database.user.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

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

    public void setTasks(Set<TaskEntity> tasks) {
        if(CollectionUtils.isNotEmpty(tasks)) {
            tasks.forEach(task -> task.setUser(this));
        }
        this.tasks = tasks;
    }
}

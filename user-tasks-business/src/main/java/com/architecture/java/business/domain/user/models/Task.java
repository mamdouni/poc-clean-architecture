package com.architecture.java.business.domain.user.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class Task {

    private Integer id;

    private String title;

    private String description;

    private LocalDate dueDate;

    private String status;
}

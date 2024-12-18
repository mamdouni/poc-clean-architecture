package com.decathlon.domain_name.biz_ctx.domain.models;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class Task {

    private Integer id;

    private String title;

    private String description;

    private LocalDate dueDate;

    private String status;
}

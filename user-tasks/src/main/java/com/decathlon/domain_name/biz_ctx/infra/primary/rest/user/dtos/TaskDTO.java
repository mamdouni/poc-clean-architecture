package com.decathlon.domain_name.biz_ctx.infra.primary.rest.user.dtos;

import java.time.LocalDate;

public record TaskDTO(Long id, String title, String description, LocalDate dueDate, String status) {
}

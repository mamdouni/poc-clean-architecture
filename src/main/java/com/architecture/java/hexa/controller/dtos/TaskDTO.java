package com.architecture.java.hexa.controller.dtos;

import java.time.LocalDate;

public record TaskDTO(Long id, String title, String description, LocalDate dueDate, String status) {
}

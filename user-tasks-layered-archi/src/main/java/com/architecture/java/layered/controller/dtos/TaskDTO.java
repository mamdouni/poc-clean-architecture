package com.architecture.java.layered.controller.dtos;

import java.time.LocalDate;

public record TaskDTO(Long id, String title, String description, LocalDate dueDate, String status) {
}

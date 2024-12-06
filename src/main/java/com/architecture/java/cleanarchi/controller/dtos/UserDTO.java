package com.architecture.java.cleanarchi.controller.dtos;

import java.util.List;

public record UserDTO(Long id, String username, String email, List<TaskDTO> tasks) {
}

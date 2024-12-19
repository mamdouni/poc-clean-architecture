package com.architecture.java.layered.controller.dtos;

import java.util.List;

public record UserDTO(Long id, String username, String email, List<TaskDTO> tasks) {
}

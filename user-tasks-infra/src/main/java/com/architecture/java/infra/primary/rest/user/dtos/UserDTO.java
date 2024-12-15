package com.architecture.java.infra.primary.rest.user.dtos;

import java.util.List;

public record UserDTO(Long id, String username, String email, List<TaskDTO> tasks) {
}

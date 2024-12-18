package com.decathlon.domain_name.biz_ctx.infra.primary.rest.user.dtos;

import java.util.List;

public record UserDTO(Long id, String username, String email, List<TaskDTO> tasks) {
}

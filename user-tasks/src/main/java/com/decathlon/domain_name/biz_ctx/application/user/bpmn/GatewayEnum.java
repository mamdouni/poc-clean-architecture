package com.decathlon.domain_name.biz_ctx.application.user.bpmn;

import lombok.Getter;

/**
 * Enum to define the type of BPMN gateway in a workflow
 */
@Getter
public enum GatewayEnum {
   EXCLUSIVE ("Used to create alternative paths within a process flow. Only one of the paths can be taken."),
   PARALLEL ("Used to create parallel paths without checking any conditions. All paths are taken simultaneously."),
   INCLUSIVE ("Used to create alternative paths within a process flow. One or more paths can be taken based on conditions.");

   private final String description;

   GatewayEnum(final String description) {
      this.description = description;
   }
}

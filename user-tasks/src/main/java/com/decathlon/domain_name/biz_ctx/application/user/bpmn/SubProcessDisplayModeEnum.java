package com.decathlon.domain_name.biz_ctx.application.user.bpmn;

import lombok.Getter;

/**
 * Enum to define the Color of BPMN element
 */
@Getter
public enum SubProcessDisplayModeEnum {
   EXPANDED ("The subprocess is visible"),
   COLLAPSED ("The subprocess is not visible");

   private final String description;

   SubProcessDisplayModeEnum(final String description) {
      this.description = description;
   }
}

package com.decathlon.domain_name.biz_ctx.application.user.bpmn;

import lombok.Getter;

/**
 * Enum to define the Color of BPMN element
 */
@Getter
public enum BpmnColorEnum {
   WHITE ("#FFFFFF"),
   BLACK ("#000000"),
   RED ("#FF0000"),
   GREEN ("#00FF00"),
   BLUE ("#0000FF"),
   YELLOW ("#FFFF00"),
   ORANGE ("#FFA500"),
   PURPLE ("#800080"),
   CYAN ("#00FFFF"),
   MAGENTA ("#FF00FF"),
   BROWN ("#A52A2A"),
   GREY ("#808080"),
   PINK ("#FFC0CB"),
   LIME ("#00FF00"),
   TEAL ("#008080"),
   INDIGO ("#4B0082"),
   MAROON ("#800000"),
   OLIVE ("#808000"),
   NAVY ("#000080"),
   AQUA ("#00FFFF"),
   SILVER ("#C0C0C0");

   private final String colorCode;

   BpmnColorEnum(final String colorCode) {
      this.colorCode = colorCode;
   }
}

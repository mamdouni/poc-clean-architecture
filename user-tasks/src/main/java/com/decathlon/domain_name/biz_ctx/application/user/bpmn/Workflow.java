package com.decathlon.domain_name.biz_ctx.application.user.bpmn;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface Workflow {
   String name();

   String title() default "";

   String documentation() default "";

   String startEventTextAnnotation() default "";
   BpmnColorEnum startEventTextAnnotationFgColor() default BpmnColorEnum.BLACK;
   BpmnColorEnum startEventTextAnnotationBgColor() default BpmnColorEnum.WHITE;

   String endEventTextAnnotation() default "";
   BpmnColorEnum endEventTextAnnotationFgColor() default BpmnColorEnum.BLACK;
   BpmnColorEnum endEventTextAnnotationBgColor() default BpmnColorEnum.WHITE;

}

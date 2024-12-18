package com.decathlon.domain_name.biz_ctx.application.user.bpmn;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to define a process step in a workflow
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProcessStep {
   String name();

   String documentation() default "";

   BpmnColorEnum fgColor() default BpmnColorEnum.BLACK;
   BpmnColorEnum bgColor() default BpmnColorEnum.WHITE;

   String textAnnotation() default "";
   BpmnColorEnum textAnnotationFgColor() default BpmnColorEnum.BLACK;
   BpmnColorEnum textAnnotationBgColor() default BpmnColorEnum.WHITE;

}

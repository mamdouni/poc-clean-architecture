package com.decathlon.domain_name.biz_ctx.domain_name.biz_ctx;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Tag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@DisplayNameGeneration(ReplaceCamelCase.class)
@Tag(UnitTest.UNIT_TEST)
public @interface UnitTest {
   String UNIT_TEST = "UNIT_TEST";
}

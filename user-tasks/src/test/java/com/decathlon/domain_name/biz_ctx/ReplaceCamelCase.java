package com.decathlon.domain_name.biz_ctx;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

public class ReplaceCamelCase extends DisplayNameGenerator.Standard {

   @Override
   public String generateDisplayNameForMethod(final Class<?> testClass, final Method testMethod) {
      return this.replaceCapitals(testMethod.getName());
   }

   private String replaceCapitals(String name) {
      name = name.replaceAll("([A-Z])", " $1");
      name = name.replaceAll("([0-9]+)", " $1");
      name = name.replaceAll("([_]+)", " ");
      name = name.toLowerCase();
      return name;
   }
}

package com.decathlon.domain_name.biz_ctx;

import com.decathlon.domain_name.technical.core.BusinessContext;
import com.decathlon.domain_name.technical.core.SharedKernel;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UnitTest
public class HexagonalArchTest {
   public static final String ROOT_PACKAGE = "com.decathlon";

   private static final JavaClasses classes = new ClassFileImporter()
         .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
         .importPackages(ROOT_PACKAGE);

   private static final Collection<String> businessContexts      = packagesWithAnnotation(BusinessContext.class);
   private static final Collection<String> sharedKernels         = packagesWithAnnotation(SharedKernel.class);
   private static final Collection<String> sharedKernelsPackages = buildPackagesPatterns(sharedKernels);

   // the empty package is related to: https://github.com/TNG/ArchUnit/issues/191#issuecomment-507964792
   private static final Collection<String> vanillaPackages             = List.of("java..", "");
   private static final Collection<String> commonToolsAndUtilsPackages = List.of(
         "org.apache.commons..",
         "com.google.guava..",
         "lombok..",
         "com.fasterxml.jackson.annotation..",
         "org.springframework.stereotype.."
                                                                                );


   private static final Collection<String> excludedPackages = List.of(
         //  "org.slf4j..",
         //  "lombok.extern.slf4j.."
                                                                     );

   private static Collection<String> buildPackagesPatterns(final Collection<String> packages) {
      return packages.stream()
                     .map(path -> path + "..")
                     .toList();
   }

   private static Collection<String> packagesWithAnnotation(final Class<? extends Annotation> annotationClass) throws
                                                                                                               AssertionError {
      try {
         return Files
               .walk(Paths.get("src", "main", "java", "com", "decathlon"))
               .filter(path -> path.toString()
                                   .endsWith("package-info.java"))
               .map(toPackageName())
               .map(path -> path.replaceAll("[\\/]", "."))
               .map(path -> path.replaceAll("[\\\\]", "."))
               .map(path -> path.replace("src.main.java.", ""))
               .map(toPackage())
               .filter(pack -> pack.getAnnotation(annotationClass) != null)
               .map(Package::getName)
               .toList();
      }
      catch (final IOException e) {
         throw new AssertionError(e);
      }
   }

   private static Function<Path, String> toPackageName() {
      return path -> {
         final String stringPath = path.toString();
         return stringPath.substring(0, stringPath.lastIndexOf("."));
      };
   }

   private static Function<String, Package> toPackage() {
      return path -> {
         try {
            return Class.forName(path)
                        .getPackage();
         }
         catch (final ClassNotFoundException e) {
            throw new AssertionError();
         }
      };
   }

   @Nested
   class All {
      @Test
      void shouldNotDependOnPackage() {
         final List<String> concatenatedList = Stream.of(businessContexts, sharedKernels)
                                                     .flatMap(Collection::stream)
                                                     .collect(Collectors.toList());

         concatenatedList.stream()
                         .forEach(context -> {
                            ArchRuleDefinition.noClasses()
                                              .that()
                                              .resideInAnyPackage(context + "..")
                                              .should()
                                              .dependOnClassesThat()
                                              .resideInAnyPackage(forbiddenPackages())
                                              .because(
                                                    "Classes can't depend on classes defined in the excluded packages")
                                              .check(classes);
                         });
      }

      private String[] forbiddenPackages() {
         return Stream
               .of(excludedPackages)
               .flatMap(Collection::stream)
               .toArray(String[]::new);
      }

   }

   @Nested
   class BoundedContexts {

      @Test
      void shouldNotDependOnOtherBoundedContextDomains() {
         final List<String> concatenatedList = Stream.of(businessContexts, sharedKernels)
                                                     .flatMap(Collection::stream)
                                                     .collect(Collectors.toList());
         concatenatedList.stream()
                         .forEach(context -> {
                            ArchRuleDefinition.noClasses()
                                              .that()
                                              .resideInAnyPackage(context + "..")
                                              .and()
                                              .areNotAnnotatedWith(HexaArchImportInclude.class)
                                              .should()
                                              .dependOnClassesThat()
                                              .resideInAnyPackage(otherBusinessContextsDomains(context))
                                              .because(
                                                    "Contexts can only depend on classes in the same context or shared kernels")
                                              .check(classes);
                         });
      }

      @Test
      void shouldBeAnHexagonalArchitecture() {
         final List<String> concatenatedList = Stream.of(businessContexts, sharedKernels)
                                                     .flatMap(Collection::stream)
                                                     .collect(Collectors.toList());
         concatenatedList.stream()
                         .forEach(context -> Architectures
                                        .layeredArchitecture()
                                        .consideringOnlyDependenciesInAnyPackage(context + "..")
                                        .withOptionalLayers(true)
                                        .layer("domain models")
                                        .definedBy(context + ".domain..")
                                        .layer("domain services")
                                        .definedBy(context + ".domain..")
                                        .layer("application services")
                                        .definedBy(context + ".application..")
                                        .layer("primary adapters")
                                        .definedBy(context + ".infra.primary..")
                                        .layer("secondary adapters")
                                        .definedBy(context + ".infra.secondary..")
                                        .whereLayer("application services")
                                        .mayOnlyBeAccessedByLayers("primary adapters")
                                        .whereLayer("primary adapters")
                                        .mayNotBeAccessedByAnyLayer()
                                        .whereLayer("secondary adapters")
                                        .mayNotBeAccessedByAnyLayer()
                                        .because("Each bounded context should implement an hexagonal architecture")
                                        .check(classes)
                                 );
      }

      @Test
      void primaryJavaAdaptersShouldOnlyBeCalledFromSecondaries() {
         ArchRuleDefinition.classes()
                           .that()
                           .resideInAPackage("..primary..")
                           .and()
                           .areMetaAnnotatedWith(Component.class)
                           .and()
                           .haveSimpleNameStartingWith("Java")
                           .should()
                           .onlyHaveDependentClassesThat()
                           .resideInAPackage("..secondary..")
                           .because(
                                 "To interact between two contexts, secondary from context 'A' " +
                                 "should call a primary Java adapter (naming convention starting with 'Java') " +
                                 "from context 'B'"
                                   )
                           .check(classes);
      }

      private String[] otherBusinessContextsDomains(final String context) {
         return businessContexts.stream()
                                .filter(other -> !context.equals(other))
                                .map(name -> name + ".domain..")
                                .toArray(String[]::new);
      }
   }

   @Nested
   class Domain {

      @Test
      void shouldNotDependOnOutside() {
         ArchRuleDefinition.classes()
                           .that()
                           .resideInAPackage("..domain..")
                           .should()
                           .onlyDependOnClassesThat()
                           .resideInAnyPackage(authorizedDomainPackages())
                           .because("Domain model should only depend on himself and a very limited set of external dependencies")
                           .check(classes);
      }

      private String[] authorizedDomainPackages() {
         return Stream
               .of(List.of("..domain.."), vanillaPackages, commonToolsAndUtilsPackages, sharedKernelsPackages)
               .flatMap(Collection::stream)
               .toArray(String[]::new);
      }
   }

   @Nested
   class Application {

      @Test
      void shouldNotDependOnInfrastructure() {
         ArchRuleDefinition.noClasses()
                           .that()
                           .resideInAPackage("..application..")
                           .should()
                           .dependOnClassesThat()
                           .resideInAPackage("..infra..")
                           .because("Application should only depend on domain, not on infrastructure")
                           .check(classes);
      }

      @Test
      void shouldNotDependOnCamundaSpi() {
         ArchRuleDefinition.noClasses()
                           .that()
                           .resideInAPackage("..application..")
                           .should()
                           .dependOnClassesThat()
                           .resideInAPackage("..camunda.spi..")
                           .because("Application should only depend on domain, not on Camunda SPI")
                           .check(classes);
      }
   }

   @Nested
   class Primary {

      @Test
      void shouldNotDependOnSecondary() {
         ArchRuleDefinition.noClasses()
                           .that()
                           .resideInAPackage("..primary..")
                           .should()
                           .dependOnClassesThat()
                           .resideInAPackage("..secondary..")
                           .because("Primary should not interact with secondary")
                           .check(classes);
      }

      @Test
      void shouldNotHavePublicControllers() {
         ArchRuleDefinition.noClasses().that()
                           .areAnnotatedWith(RestController.class)
                           .or()
                           .areAnnotatedWith(Controller.class)
                           .should()
                           .bePublic()
                           .check(classes);
      }
   }

   @Nested
   class Secondary {

      @Test
      void shouldNotDependOnApplication() {
         ArchRuleDefinition.noClasses()
                           .that()
                           .resideInAPackage("..infra.secondary..")
                           .should()
                           .dependOnClassesThat()
                           .resideInAPackage("..application..")
                           .because("Secondary should not depend on application")
                           .check(classes);
      }

      @Test
      void shouldNotDependOnSameContextPrimary() {
         final List<String> concatenatedList = Stream.of(businessContexts, sharedKernels)
                                                     .flatMap(Collection::stream)
                                                     .collect(Collectors.toList());
         concatenatedList.stream()
                         .forEach(context -> {
                            ArchRuleDefinition.noClasses()
                                              .that()
                                              .resideInAPackage(context + ".infra.secondary..")
                                              .should()
                                              .dependOnClassesThat()
                                              .resideInAPackage(context + ".infra.primary")
                                              .because("Secondary should not loop to its own context's primary")
                                              .check(classes);
                         });
      }
   }
}

module com.architecture.java.container {

    requires com.architecture.java.business;
    requires lombok;
    requires spring.context;
    requires spring.data.jpa;
    requires spring.boot;
    requires spring.core;
    requires spring.beans;
    requires spring.tx;
    requires spring.aop;
    requires jakarta.annotation;
    requires java.sql;
    requires org.aspectj.weaver;
    requires spring.boot.autoconfigure;
    requires org.apache.commons.lang3;
    requires org.slf4j;

    opens com.architecture.java.container.config to spring.core,spring.beans,spring.context,spring.web,spring.aop,com.fasterxml.jackson.databind;
    opens com.architecture.java.container to spring.core,spring.beans,spring.context,spring.web,spring.aop,com.fasterxml.jackson.databind;
    // This will open the com.architecture.java.container package to all unnamed modules, allowing reflection-based access.

}
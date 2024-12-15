module com.architecture.java.infra {

    requires com.architecture.java.business;
    requires spring.context;
    requires org.apache.commons.lang3;
    requires spring.web;
    requires spring.data.jpa;
    requires spring.webmvc;
    requires jakarta.persistence;
    requires java.sql;
    requires spring.beans;
    requires lombok;
    requires org.mapstruct;
    requires org.apache.commons.collections4;


    opens com.architecture.java.infra.primary.rest.config.errorhandler to spring.beans;
    opens com.architecture.java.infra.primary.rest.ping to spring.beans;
    opens com.architecture.java.infra.primary.rest.user.controllers to spring.beans;
    opens com.architecture.java.infra.secondary.database.user.repositories to spring.beans;
    opens com.architecture.java.infra.secondary.database.user.entities to spring.beans;
}
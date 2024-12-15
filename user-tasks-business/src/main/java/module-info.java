module com.architecture.java.business {

    exports com.architecture.java.business.application.user.ports.inputs;
    exports com.architecture.java.business.domain.user.ports.outputs;
    exports com.architecture.java.business.domain.user.models;
    exports com.architecture.java.business.domain.exceptions;
    requires lombok;
    requires spring.context;
    requires org.apache.commons.lang3;
    requires org.mapstruct;

    opens com.architecture.java.business.domain.user.rules to spring.beans;
    opens com.architecture.java.business.application.user.service to spring.beans; // This will open the com.architecture.java.business.application.user.service package to all unnamed modules, allowing reflection-based access.
}
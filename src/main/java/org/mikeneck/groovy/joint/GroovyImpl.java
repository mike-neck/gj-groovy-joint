package org.mikeneck.groovy.joint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation class for an interface requiring to be implemented by a groovy class.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GroovyImpl {

    /**
     * Indicates the class name whose groovy class should implement an interface annotated.<br/>
     * The class name should be given FQCN to.
     * @return
     */
    public String implementedBy() default "";
}

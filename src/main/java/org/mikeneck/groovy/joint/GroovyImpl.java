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
     * Indicates the groovy class name which implements the interface.<br/>
     * The class name should be given FQCN to.
     * @return groovy class name.
     */
    public String implementedBy() default "";

    /**
     * The use scope for the implement class. <br/>
     * If you want to use as singleton give {@code true} to this. If not give {@code false}.<br/>
     * {@code true} is given in default.
     * @return scope.
     */
    public boolean singleton () default true;
}

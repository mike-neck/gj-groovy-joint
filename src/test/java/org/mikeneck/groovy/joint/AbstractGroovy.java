package org.mikeneck.groovy.joint;

/**
 */
@GroovyImpl (implementedBy = "org.mikeneck.groovy.joint.AbstractGroovyClass")
public abstract class AbstractGroovy {

    abstract public String groovy ();

    public String getParent () {
        return "parent";
    }
}

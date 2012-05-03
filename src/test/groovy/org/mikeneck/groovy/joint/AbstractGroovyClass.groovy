package org.mikeneck.groovy.joint

/**
 */
class AbstractGroovyClass extends AbstractGroovy {
    @Override
    String groovy() {
        "${getParent()}-groovy"
    }
}

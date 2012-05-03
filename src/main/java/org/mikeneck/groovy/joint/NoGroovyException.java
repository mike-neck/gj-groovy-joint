package org.mikeneck.groovy.joint;

/**
 * Exception for the miss indicating of groovy class.
 */
public class NoGroovyException extends RuntimeException {

    private Class<?> interfaceClass;

    private String groovyClass = "";

    public NoGroovyException (Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public NoGroovyException (Class<?> interfaceClass, String groovyClass) {
        this.interfaceClass = interfaceClass;
        this.groovyClass = groovyClass;
    }

    public NoGroovyException (Class<?> interfaceClass, String groovyClass, Throwable throwable) {
        super(throwable);
        this.interfaceClass = interfaceClass;
        this.groovyClass = groovyClass;
    }

    @Override
    public String getMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append(interfaceClass.getName())
                .append(" needs implemented");
        if ("".equals(groovyClass)) {
            builder.append(".");
        } else {
            builder.append(" by ")
                    .append(groovyClass);
        }
        return builder.toString();
    }
}

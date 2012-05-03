package org.mikeneck.groovy.joint;

/**
 * The groovy joint injector.<br/>
 * This class injects a groovy implement to a java interface with annotation.
 *
 * @since 1.0.0
 * @author mike (mike@mikeneck.org)
 */
public class GroovyJoint {

    private static ClassLoader loader = GroovyJoint.class.getClassLoader();

    public static <T> T getImplementOf(Class<T> klass) throws NoGroovyException {
        GroovyImpl groovy = klass.getAnnotation(GroovyImpl.class);
        if (groovy == null) {
            throw new NoGroovyException(klass);
        }
        String impl = groovy.implementedBy();
        if ("".equals(impl)) {
            throw new NoGroovyException(klass, impl);
        }
        try {
            Class<?> groovyClass = loader.loadClass(impl);
            Object instance = groovyClass.newInstance();
            return klass.cast(instance);
        } catch (ClassCastException e) {
            throw new NoGroovyException(klass, impl, e);
        } catch (ClassNotFoundException e) {
            throw new NoGroovyException(klass, impl, e);
        } catch (InstantiationException e) {
            throw new NoGroovyException(klass, impl, e);
        } catch (IllegalAccessException e) {
            throw new NoGroovyException(klass, impl, e);
        }
    }
}

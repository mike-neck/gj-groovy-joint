package org.mikeneck.groovy.joint;

import java.util.HashMap;
import java.util.Map;

/**
 * The groovy joint injector.<br/>
 * This class injects a groovy implement to a java interface with annotation.
 *
 * @since 1.0.0
 * @author mike (mike@mikeneck.org)
 */
public class GroovyJoint {

    private static ClassLoader loader = GroovyJoint.class.getClassLoader();

    private static Map <GroovyImpl, Class<?>> klasses = new HashMap<>();

    private static Map <GroovyImpl, Object> objects = new HashMap<>();

    public static <T> T getImplementOf(Class<T> klass) throws NoGroovyException {
        GroovyImpl groovy = klass.getAnnotation(GroovyImpl.class);
        if (groovy == null) {
            throw new NoGroovyException(klass);
        }
        String impl = groovy.implementedBy();
        boolean singleton = groovy.singleton();
        if ("".equals(impl)) {
            throw new NoGroovyException(klass, impl);
        }
        if (singleton) {
            if (objects.containsKey(groovy)) {
                return klass.cast(objects.get(groovy));
            }
        }
        try {
            Class<?> groovyClass;
            if (klasses.containsKey(groovy)) {
                groovyClass = klasses.get(groovy);
            } else {
                groovyClass = loader.loadClass(impl);
            }
            Object instance = groovyClass.newInstance();
            if (singleton) {
                objects.put(groovy, instance);
            }
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

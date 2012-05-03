package org.mikeneck.groovy.joint

import org.junit.Test

/**
 * test for {@link GroovyJoint}
 */
class GroovyJointTest {

    @Test(expected = NoGroovyException.class)
    public void noAnnotation () {
        try {
            GroovyJoint.getImplementOf(NoAnnotation.class)
        } catch (NoGroovyException e) {
            assert e.interfaceClass == NoAnnotation.class
            assert e.groovyClass == ''
            throw e;
        }
    }

    @Test (expected = NoGroovyException.class)
    void noGroovyClass () {
        try {
            GroovyJoint.getImplementOf(NoGroovyClass.class)
        } catch (NoGroovyException e) {
            assert e.interfaceClass == NoGroovyClass.class
            assert e.groovyClass == ''
            throw e
        }
    }

    @Test (expected = NoGroovyException.class)
    void noGroovyImplClass () {
        try {
            GroovyJoint.getImplementOf(NoGroovyImplClass.class)
        } catch (NoGroovyException e) {
            assert e.interfaceClass == NoGroovyImplClass.class
            assert e.groovyClass == 'hoge.NoGroovy'
            throw e
        }
    }

    @Test (expected = NoGroovyException.class)
    void noConstructor () {
        try {
            GroovyJoint.getImplementOf(NoConstructor.class)
        } catch (NoGroovyException e) {
            assert e.interfaceClass == NoConstructor.class
            assert e.groovyClass == 'org.mikeneck.groovy.joint.NoConstructorImpl'
            assert e.cause.class == InstantiationException.class
            throw e
        }
    }

    @Test (expected = NoGroovyException.class)
    void illegalAccess () {
        try {
            GroovyJoint.getImplementOf(IllegalAccess.class)
        } catch (NoGroovyException e) {
            assert e.interfaceClass == IllegalAccess.class
            assert e.groovyClass == 'org.mikeneck.groovy.joint.IllegalAccessClass'
            assert e.cause.class == IllegalAccessException.class
            throw e
        }
    }

    @Test (expected = NoGroovyException.class)
    void incompatibleClass () {
        try {
            GroovyJoint.getImplementOf(Incompatible.class)
        } catch (NoGroovyException e) {
            assert e.interfaceClass == Incompatible.class
            assert e.groovyClass == 'org.mikeneck.groovy.joint.IncompatibleClass'
            assert e.cause.class == ClassCastException.class
            throw e
        }
    }

    @Test
    void javaJoint () {
        def joint = GroovyJoint.getImplementOf(JavaJoint.class)
        assert joint != null
        assert joint instanceof JavaJointClass
    }

    @Test
    void groovy () {
        def groovy = GroovyJoint.getImplementOf(Groovy.class)
        assert groovy.groovy() == 'groovy'
    }

    @Test
    void abstractGroovy () {
        def groovy = GroovyJoint.getImplementOf(AbstractGroovy.class)
        assert groovy.groovy() == 'parent-groovy'
    }

    @Test
    void classLoaderIsNotNull () {
        assert GroovyJoint.loader != null
    }
}

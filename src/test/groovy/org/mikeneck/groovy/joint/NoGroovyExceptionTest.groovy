package org.mikeneck.groovy.joint

import org.junit.Test

/**
 * Test class for {@link NoGroovyException}.
 */
class NoGroovyExceptionTest {

    private static final String CLASS_NAME = 'NoGroovyExceptionTest'

    @Test
    public void contructor () {
        def exception = new NoGroovyException(this.class)
        assert exception != null
        assert exception.interfaceClass == this.class
        assert exception.groovyClass == ''
    }

    @Test
    public void constructorWithGroovyAnnotation () {
        def exception = new NoGroovyException(this.class, 'hoge.Hoge')
        assert exception != null
        assert exception.interfaceClass == this.class
        assert exception.groovyClass == 'hoge.Hoge'
    }

    @Test
    public void constructorWithExceptionAnnotation () {
        def exception = new NoGroovyException(this.class, 'hoge.Hoge', new Exception())
        assert exception != null
        assert exception.interfaceClass == this.class
        assert exception.groovyClass == 'hoge.Hoge'
        assert exception.getCause().class == Exception.class
    }

    @Test
    public void noGroovyClassGetMessage () {
        def exception = new NoGroovyException(this.class)
        def message = exception.getMessage()
        assert message != null
        assert message.contains(CLASS_NAME)
    }

    @Test
    public void withGroovyClassGetMessage () {
        def exception = new NoGroovyException(this.class, 'hoge.Hoge')
        def message = exception.getMessage()
        assert message != null
        assert message.contains(CLASS_NAME)
        assert message.contains('hoge.Hoge')
    }
}

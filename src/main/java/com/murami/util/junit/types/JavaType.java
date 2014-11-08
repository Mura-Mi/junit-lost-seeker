package com.murami.util.junit.types;

import java.util.Collection;

/**
 * An interface that represents Java Type.
 * 
 * @author takuyamurakami
 *
 */
@FunctionalInterface
public interface JavaType {

    /**
     * Aggregate all {@link JavaType}s which is runnable as an test suite.
     * 
     * @return test classes.
     */
    Collection<JavaType> getTestClass();

}

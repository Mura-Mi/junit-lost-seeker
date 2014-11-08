package com.murami.util.junit.types.impl;

import com.murami.util.junit.types.JavaType;

/**
 * Convert {@link Class} type to {@link JavaType} type to enable to apply
 * composite pattern.
 * 
 * @author takuyamurakami
 *
 */
public final class JavaTypeFactory {

    private JavaTypeFactory() {
    }

    public static JavaType resolveJavaType(Class<?> target) {
        if (TestSuiteType.isTarget(target)) {
            return new TestSuiteType(target);
        } else if (SingleTestCaseType.isTarget(target)) {
            return new SingleTestCaseType(target);
        } else {
            return new NonTestCaseJavaType(target);
        }
    }
}

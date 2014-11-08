package com.murami.util.junit.types.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.base.Preconditions;
import com.murami.util.junit.types.JavaType;

class SingleTestCaseType extends AbstractJavaType {

    SingleTestCaseType(Class<?> clazz) {
        super(clazz);
        Preconditions.checkArgument(isTarget(clazz));
    }

    @Override
    public Collection<JavaType> getTestClass() {
        return Collections.singleton(this);
    }

    static boolean isTarget(Class<?> clazz) {
        boolean hasTestAnnotated = Arrays.stream(clazz.getMethods()).anyMatch(
                m -> m.getAnnotation(Test.class) != null);
        boolean hasRunner = clazz.getAnnotation(RunWith.class) != null;

        return hasTestAnnotated || hasRunner;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SingleTestCaseType
                && ((SingleTestCaseType) obj).getOriginalType().equals(
                        this.getOriginalType());
    }

}

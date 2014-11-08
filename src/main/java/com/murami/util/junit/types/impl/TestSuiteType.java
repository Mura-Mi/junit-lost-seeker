package com.murami.util.junit.types.impl;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.runners.Suite.SuiteClasses;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.murami.util.junit.types.JavaType;

class TestSuiteType extends AbstractJavaType {

    private final Collection<JavaType> testClasses;

    TestSuiteType(Class<?> clazz) {
        super(clazz);
        Preconditions.checkArgument(isTarget(clazz));

        SuiteClasses suite = clazz.getAnnotation(SuiteClasses.class);
        this.testClasses = Sets.newHashSet();
        Arrays.stream(suite.value()).forEach(
                t -> testClasses.addAll(JavaTypeFactory.resolveJavaType(t)
                        .getTestClass()));

    }

    static boolean isTarget(Class<?> clazz) {
        return Arrays.stream(clazz.getAnnotations()).anyMatch(
                an -> an instanceof SuiteClasses);
    }

    @Override
    public Collection<JavaType> getTestClass() {
        return testClasses;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TestSuiteType
                && ((TestSuiteType) obj).getOriginalType().equals(
                        this.getOriginalType());
    }

}

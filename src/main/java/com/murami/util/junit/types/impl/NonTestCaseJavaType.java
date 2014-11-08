package com.murami.util.junit.types.impl;

import java.util.Collection;
import java.util.Collections;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.murami.util.junit.types.JavaType;

public class NonTestCaseJavaType extends AbstractJavaType {

    NonTestCaseJavaType(Class<?> clazz) {
        super(clazz);
    }

    @Override
    public Collection<JavaType> getTestClass() {
        return Collections.emptySet();
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NonTestCaseJavaType
                && ((NonTestCaseJavaType) obj).getOriginalType().equals(
                        this.getOriginalType());
    }
}

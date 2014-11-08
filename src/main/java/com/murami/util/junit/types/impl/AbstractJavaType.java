package com.murami.util.junit.types.impl;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.murami.util.junit.types.JavaType;

abstract class AbstractJavaType implements JavaType {
    private final Class<?> clazz;

    AbstractJavaType(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    protected Class<?> getOriginalType() {
        return clazz;
    }
}

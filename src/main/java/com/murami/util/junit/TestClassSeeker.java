package com.murami.util.junit;

import java.io.IOException;
import java.util.Collection;

import com.google.common.collect.Sets;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import com.murami.util.junit.internal.ReflectionUtils;
import com.murami.util.junit.types.JavaType;
import com.murami.util.junit.types.impl.JavaTypeFactory;

public class TestClassSeeker {

    public static void main(String args[]) throws IOException,
            ClassNotFoundException {
        if (args.length == 0) {
            System.exit(1);
        }

        Package pkg = Class.forName(args[0]).getPackage();

        ClassPath path = ClassPath.from(ClassLoader.getSystemClassLoader());
        // TODO filter by package

        Collection<JavaType> allTestClasses = Sets.newHashSet();

        path.getAllClasses().stream().map(ClassInfo::load)
                .filter((clz) -> ReflectionUtils.isInPackage(clz, pkg))
                .map(JavaTypeFactory::resolveJavaType) //
                .map(JavaType::getTestClass) //
                .forEach(allTestClasses::addAll);
    }

}

package com.murami.util.junit.internal;

public final class ReflectionUtils {
    private ReflectionUtils() {
    };

    public static boolean isInPackage(Class<?> clazz, Package pkg) {
        return clazz.getPackage().getName().matches("^" + pkg.getName() + ".*");
    }

}

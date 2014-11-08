package com.murami.util.junit.internal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import com.murami.util.junit.TestClassSeeker;

public class ReflectionUtilsTest {

    @Test
    public void testIsInPackage() {
        assertThat(ReflectionUtils.isInPackage(ReflectionUtilsTest.class,
                TestClassSeeker.class.getPackage()), is(true));
    }
}

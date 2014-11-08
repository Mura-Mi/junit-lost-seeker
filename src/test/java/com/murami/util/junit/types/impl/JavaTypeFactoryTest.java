package com.murami.util.junit.types.impl;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.murami.util.junit.types.JavaType;

public class JavaTypeFactoryTest {

    @Test
    public void testResolveJavaTypeNonTestClass() {
        JavaType cls = JavaTypeFactory.resolveJavaType(MyNonTestClass.class);
        assertThat(cls, is(instanceOf(NonTestCaseJavaType.class)));
        assertThat(cls.getTestClass(), is(Matchers.empty()));
    }

    @Test
    public void testResolveJavaTypeTestClass() {
        JavaType cls = JavaTypeFactory.resolveJavaType(MyTestSuite.class);
        assertThat(cls, is(instanceOf(TestSuiteType.class)));
        assertThat(cls.getTestClass(), Matchers.hasSize(2));
        assertThat(cls.getTestClass(),
                Matchers.hasItem(new SingleTestCaseType(MyTestClass1.class)));
        assertThat(cls.getTestClass(),
                Matchers.hasItem(new SingleTestCaseType(MyTestClass2.class)));
    }

    @Test
    public void testResolveJavaTypeSingleTestCase() {
        JavaType cls = JavaTypeFactory.resolveJavaType(MyTestClass1.class);
        assertThat(cls, is(instanceOf(SingleTestCaseType.class)));
        assertThat(cls.getTestClass(), Matchers.hasSize(1));
        assertThat(cls.getTestClass(), Matchers.hasItem(cls));
    }

    @Test
    public void testResolveJavaTypeSingleTestRunnable() {
        JavaType cls = JavaTypeFactory.resolveJavaType(MyTestClass2.class);
        assertThat(cls, is(instanceOf(SingleTestCaseType.class)));
        assertThat(cls.getTestClass(), Matchers.hasSize(1));
        assertThat(cls.getTestClass(), Matchers.hasItem(cls));
    }

    @Test
    public void testResolveJavaTypeSecondLevelEnclosingSuite() {
        JavaType cls = JavaTypeFactory
                .resolveJavaType(MySecondLevelTestSuite.class);
        assertThat(cls, is(instanceOf(TestSuiteType.class)));
        assertThat(cls.getTestClass(), Matchers.hasSize(3));
        assertThat(cls.getTestClass(),
                Matchers.hasItem(new SingleTestCaseType(MyTestClass1.class)));
        assertThat(cls.getTestClass(),
                Matchers.hasItem(new SingleTestCaseType(MyTestClass2.class)));
        assertThat(cls.getTestClass(),
                Matchers.hasItem(new SingleTestCaseType(MyTestClass3.class)));
        assertThat(cls.getTestClass(),
                not(Matchers.contains(new TestSuiteType(MyTestSuite.class))));
    }

    private class MyTestClass1 {
        @Test
        public void test() {
        }
    }

    @RunWith(Theories.class)
    private class MyTestClass2 {
        @Theory
        public void test() {
        }
    }

    @RunWith(Enclosed.class)
    private class MyTestClass3 {

    }

    @SuiteClasses({ MyTestClass1.class, MyTestClass2.class })
    private class MyTestSuite {
    }

    @SuiteClasses({ MyTestSuite.class, MyTestClass3.class })
    private class MySecondLevelTestSuite {

    }

    private class MyNonTestClass {
        @SuppressWarnings("unused")
        public void run() {
        }
    }
}

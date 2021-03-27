package ru.geekbrains.java.reflectiondemo;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Tester {

    public static void start(Class testClass) {
        Method[] methods = testClass.getMethods();

        int beforeSuiteCount = 0;
        int afterSuiteCount = 0;

        ArrayList<Method> testMethods = new ArrayList<>();
        Method beforeSuiteMethod = null;
        Method afterSuiteMethod = null;

        for (Method method : methods) {
            Annotation beforeSuiteAnnotation = method.getAnnotation(BeforeSuite.class);
            if (beforeSuiteAnnotation != null) {
                beforeSuiteMethod = method;
                beforeSuiteCount++;
            }

            Annotation afterSuiteAnnotation = method.getAnnotation(AfterSuite.class);
            if (afterSuiteAnnotation != null) {
                afterSuiteMethod = method;
                afterSuiteCount++;
            }

            Annotation testAnnotation = method.getAnnotation(Test.class);
            if (testAnnotation != null) {
                testMethods.add(method);
            }
        }
        if (beforeSuiteCount > 1) {
            throw new RuntimeException("There must be no more than one method annotated with @BeforeSuite!");
        }
        if (afterSuiteCount > 1) {
            throw new RuntimeException("There must be no more than one method annotated with @AfterSuite!");
        }

        TestCalculator tc = new TestCalculator();
        try {
            if (beforeSuiteMethod != null) {
                beforeSuiteMethod.invoke(tc);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        // sort testMethods by priority
        for (int i = Test.MIN_PRIORITY; i <= Test.MAX_PRIORITY; i++) {
            for (Method method : testMethods) {
                if (method.getAnnotation(Test.class).priority() == i) {
                    try {
                        method.invoke(tc);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        try {
            if (afterSuiteMethod != null) {
                afterSuiteMethod.invoke(tc);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Tester.start(TestCalculator.class);
    }
}

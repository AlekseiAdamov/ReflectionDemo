package ru.geekbrains.java.reflectiondemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
    int MIN_PRIORITY = 1;
    int MAX_PRIORITY = 10;
    int DEFAULT_PRIORITY = 5;
    int priority() default DEFAULT_PRIORITY;
}

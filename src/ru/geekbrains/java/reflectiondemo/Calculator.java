package ru.geekbrains.java.reflectiondemo;

public class Calculator {

    public static float add(float a, float b) {
        return a + b;
    }

    public static float subtract(float a, float b) {
        return a - b;
    }

    public static float multiply(float a, float b) {
        return a * b;
    }

    public static float divide(float a, float b) {
        if (b != 0) {
            return a / b;
        } else {
            return 0;
        }
    }
}

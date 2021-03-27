package ru.geekbrains.java.reflectiondemo;

public class TestCalculator {
    private Calculator calc;

    @BeforeSuite
    public void init() {
        calc = new Calculator();
        System.out.println("Before tests");
    }

    @Test(priority = 3)
    public void testAdd() {
        float expected = 5.0f;
        float a = 1.5f;
        float b = 3.5f;
        float actual = calc.add(a, b);
        System.out.printf("%f + %f = %f: %b\n", a, b, expected, actual == expected);
    }

    @Test
    public void testSubtract() {
        float expected = 2.0f;
        float a = 3.5f;
        float b = 1.5f;
        float actual = calc.subtract(a, b);
        System.out.printf("%f - %f = %f: %b\n", a, b, expected, actual == expected);
    }

    @Test(priority = 2)
    public void testMultiply() {
        float expected = 5.0f;
        float a = 2.5f;
        float b = 2.0f;
        float actual = calc.multiply(a, b);
        System.out.printf("%f * %f = %f: %b\n", a, b, expected, actual == expected);
    }

    @Test
    public void testDivide() {
        float expected = 3.0f;
        float a = 5.0f;
        float b = 2.5f;
        float actual = calc.divide(a, b);
        System.out.printf("%f / %f = %f: %b\n", a, b, expected, actual == expected);
    }

    @AfterSuite
    public void end() {
        System.out.println("After tests");
    }

}

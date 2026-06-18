package com.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Calculator Tests")
class CalculatorTest {

    private Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
    }

    @Test
    @DisplayName("add: 2 + 3 = 5")
    void testAdd() {
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    @DisplayName("add: negative numbers")
    void testAddNegative() {
        assertEquals(-1, calc.add(-3, 2));
    }

    @Test
    @DisplayName("subtract: 5 - 3 = 2")
    void testSubtract() {
        assertEquals(2, calc.subtract(5, 3));
    }

    @Test
    @DisplayName("multiply: 4 * 3 = 12")
    void testMultiply() {
        assertEquals(12, calc.multiply(4, 3));
    }

    @Test
    @DisplayName("divide: 10 / 2 = 5.0")
    void testDivide() {
        assertEquals(5.0, calc.divide(10, 2), 1e-9);
    }

    @Test
    @DisplayName("divide by zero throws ArithmeticException")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(5, 0));
    }

    @Test
    @DisplayName("isPrime: 7 is prime")
    void testIsPrime() {
        assertTrue(calc.isPrime(7));
        assertFalse(calc.isPrime(4));
        assertFalse(calc.isPrime(1));
    }

    @Test
    @DisplayName("factorial: 5! = 120")
    void testFactorial() {
        assertEquals(120L, calc.factorial(5));
        assertEquals(1L, calc.factorial(0));
    }

    @Test
    @DisplayName("factorial of negative throws IllegalArgumentException")
    void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(-1));
    }
}

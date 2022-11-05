package com.example.calculator;

import com.example.Calculator;
import com.example.NumberSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {


    @Mock
    private NumberSource numberSource;

    private Calculator underTest;

    @BeforeAll
    public void beforeAll() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void beforeEach() {
        underTest = new Calculator(numberSource);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 10L, 100L, Long.MAX_VALUE})
    @DisplayName("Positive * Positive")
    public void calculator_Multiply_PositiveAndPositive_ReturnsPositive(long value) {
        Mockito.when(numberSource.next()).thenReturn(value, value);
        assertTrue(underTest.multiply() > 0);
    }

    @ParameterizedTest
    @MethodSource("providePositiveAndNegativeNumbers")
    @DisplayName("Positive * Negative")
    public void calculator_Multiply_PositiveAndNegative_returnsNegative(long value1, long value2) {
        Mockito.when(numberSource.next()).thenReturn(value1, value2);
        assertTrue(underTest.multiply() < 0);

    }
    @ParameterizedTest
    @MethodSource("provideNegativeAndPositiveNumbers")
    @DisplayName("Negative * Positive")
    public void calculator_Multiply_NegativeAndPositive_returnsNegative(long value1, long value2) {
        Mockito.when(numberSource.next()).thenReturn(value1, value2);
        assertTrue(underTest.multiply() < 0);

    }

    @ParameterizedTest
    @ValueSource(longs = {-1L, -10L, -100L, -Long.MAX_VALUE})
    public void calculator_Multiply_NegativeAndNegative_returnsPositive(long value) {
        Mockito.when(numberSource.next()).thenReturn(value, value);
        assertTrue(underTest.multiply() > 0);
    }

    private Stream<Arguments> providePositiveAndNegativeNumbers() {
        return Stream.of(
                Arguments.of(-1L, 1),
                Arguments.of(-10L, 10),
                Arguments.of(-100, 100),
                Arguments.of(-1000, 1000),
                Arguments.of(-Long.MAX_VALUE, Long.MAX_VALUE)
        );
    }
    private Stream<Arguments> provideNegativeAndPositiveNumbers() {
        return Stream.of(
                Arguments.of(-1L, 1),
                Arguments.of(-10L, 10),
                Arguments.of(-100, 100),
                Arguments.of(-1000, 1000),
                Arguments.of(-Long.MAX_VALUE, Long.MAX_VALUE)
        );
    }




}

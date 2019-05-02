package edu.umb.cs680.hw01;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    
    @Test
    public void multiply3By4() {
        Calculator cut = new Calculator();
        float actual = cut.multiply(3, 4);
        float expected = 12;
        assertEquals(actual, expected);
    }

    @Test
    public void divide3By2() {
        Calculator cut = new Calculator();
        float actual = cut.divide(3, 2);
        float expected = 1.5f;
        assertEquals(actual, expected);
    }

    @Test
    public void divideMinus1ByMinus1() {
        Calculator cut = new Calculator();
        float actual = cut.divide(-1, -1);
        float expected = 1;
        assertEquals(actual, expected);
    }

    @Test
    public void divide0By1() {
        Calculator cut = new Calculator();
        float actual = cut.divide(0, 1);
        float expected = 0;
        assertEquals(actual, expected);
    }

    @Test
    public void divide1_2By_0_5() {
        Calculator cut = new Calculator();
        float actual = cut.divide(1.2f, 0.5f);
        float expected = 2.4f;
        assertEquals(actual, expected);
    }


    @Test
    public void divide5By0() {
        Calculator cut = new Calculator();
        try {
            cut.divide(5, 0);
            fail("Division by zero failed to raise exception");
        }
        catch(IllegalArgumentException ex) {
            assertEquals("division by zero",
                         ex.getMessage());
        }
        float actual = cut.divide(3, 2);
        float expected = 1.5f;
        assertEquals(actual, expected);
    }

}




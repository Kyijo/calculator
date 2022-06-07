package cz.nevimjak.mathexpressionsolver.test;

import cz.nevimjak.mathexpressionsolver.parser.ExpressionParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTest {

    static ExpressionParser parser;

    @BeforeAll
    public static void beforeAll() {
        parser = new ExpressionParser();
    }

    @Test
    public void testEvaluateMethod01() {
        double result = parser.calculateDouble("3 + 2");

        assertEquals(5, result);
    }

    @Test
    public void testEvaluateMethod02() {
        double result = parser.calculateDouble("3 + 2 + 4");

        assertEquals(9, result);
    }

    @Test
    public void testEvaluateMethod03() {
        double result = parser.calculateDouble("3 - 2 + 4");

        assertEquals(5, result);
    }

    @Test
    public void testEvaluateMethod04() {
        double result = parser.calculateDouble("3 - 2 + 4 + 2 - 6");

        assertEquals(1, result);
    }

    @Test
    public void testEvaluateMethod05() {
        double result = parser.calculateDouble("3 * 2 + 4");

        assertEquals(10, result);
    }

    @Test
    public void testEvaluateMethod06() {
        double result = parser.calculateDouble("3 - 2 * 4");

        assertEquals(-5, result);
    }

    @Test
    public void testEvaluateMethod07() {
        double result = parser.calculateDouble("3 - 2 * 4 + 3");

        assertEquals(-2, result);
    }

    @Test
    public void testEvaluateMethod08() {
        double result = parser.calculateDouble("3 / 2 * 4");

        assertEquals(6, result);
    }

    @Test
    public void testEvaluateMethod09() {
        double result = parser.calculateDouble("9 / 3 + 2 * 4");

        assertEquals(11, result);
    }

    @Test
    public void testEvaluateMethod10() {
        double result = parser.calculateDouble("9 / 3 * 2 / 3");

        assertEquals(2, result);
    }

    @Test
    public void testEvaluateMethod11() {
        double result = parser.calculateDouble("log(999+1) * 2 / 3");

        assertEquals(2, result);
    }

    @Test
    public void testEvaluateMethod12() {
        double result = parser.calculateDouble("sqrt(36) * 2 / 3");

        assertEquals(4, result);
    }

    @Test
    public void testEvaluateMethod13() {
        double result = parser.calculateDouble("12 / (sqrt(36) - 2)");

        assertEquals(3, result);
    }

    @Test
    public void testEvaluateMethod14() {
        double result = parser.calculateDouble("20");

        assertEquals(20, result);
    }

    @Test
    public void testEvaluateMethod15() {
        double result = parser.calculateDouble("min(5,3)");

        assertEquals(3, result);
    }

    @Test
    public void testEvaluateMethod16() {
        double result = parser.calculateDouble("max(5,3) + 2");

        assertEquals(7, result);
    }

    @Test
    public void testEvaluateMethod17() {
        double result = parser.calculateDouble("max(4-1,2)");

        assertEquals(3, result);
    }

    @Test
    public void testEvaluateMethod18() {
        double result = parser.calculateDouble("10/(min(5,2))");

        assertEquals(5, result);
    }

    @Test
    public void testEvaluateMethod19() {
        double result = parser.calculateDouble("10/(min(5,2) + 3) + 2");

        assertEquals(4, result);
    }

    @Test
    public void testEvaluateMethod20() {
        double result = parser.calculateDouble("nsn(20,15)");

        assertEquals(60, result);
    }

    @Test
    public void testEvaluateMethod21() {
        double result = parser.calculateDouble("nsn(4,6)");

        assertEquals(12, result);
    }

    @Test
    public void testEvaluateMethod22() {
        double result = parser.calculateDouble("nsd(90,168)");

        assertEquals(6, result);
    }

    @Test
    public void testEvaluateMethod23() {
        double result = parser.calculateDouble("nsd(540,315)");

        assertEquals(45, result);
    }

    @Test
    public void testEvaluateMethod24() {
        double result = parser.calculateDouble("sin(5)");

        assertEquals(-0.9589242746631385, result);
    }

    @Test
    public void testEvaluateMethod25() {
        double result = parser.calculateDouble("cos(10)");

        assertEquals(-0.8390715290764524, result);
    }

    @Test
    public void testEvaluateMethod26() {
        double result = parser.calculateDouble("sqrt(9)");

        assertEquals(3, result);
    }

}
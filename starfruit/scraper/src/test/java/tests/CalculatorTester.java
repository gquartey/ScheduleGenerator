package tests;

import edu.swarthmore.cs71.starfruit.scraper.jparsec_work.CalculatorExample;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTester {
    @Test
    public void caculatorTest() throws Exception {
        assertResult(1, "1");
        assertResult(1, "(1)");
        assertResult(3, "1+2");
        assertResult(-5, "1+2*-3");
        assertResult(1, "((1-2)/-1)");
    }

    private static void assertResult(int expected, String source) {
        assertEquals(expected, CalculatorExample.evaluate(source));
    }
}

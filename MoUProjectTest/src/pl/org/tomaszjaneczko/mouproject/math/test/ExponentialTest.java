package pl.org.tomaszjaneczko.mouproject.math.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.org.tomaszjaneczko.mouproject.math.Exponential;

public class ExponentialTest {

    @Test
    public void testCoefficients() {
        Exponential exponent1 = new Exponential(-1.0);
        assertEquals(exponent1.getExponentialCoefficient(), -1.0, 0.0);

        Exponential exponent2 = new Exponential(0.0);
        assertEquals(exponent2.getExponentialCoefficient(), 0.0, 0.0);

        Exponential exponent3 = new Exponential(1.0);
        assertEquals(exponent3.getExponentialCoefficient(), 1.0, 0.0);
    }

    @Test
    public void testEquality() {
        Exponential exponent1 = new Exponential(-1.0);
        Exponential exponent2 = new Exponential(-1.0);

        assertEquals(exponent1, exponent1);
        assertEquals(exponent1, exponent2);
    }

    @Test
    public void testInequality() {
        Exponential exponent1 = new Exponential(-1.0);
        Exponential exponent2 = new Exponential(1.0);

        assertFalse(exponent1.equals(exponent2));
    }

    @Test
    public void testRendering() {
        Exponential exponent1 = new Exponential(-1.0);
        assertEquals("e^(-1.0*x)", exponent1.toString());

        Exponential exponent2 = new Exponential(0.0);
        assertEquals("1", exponent2.toString());

        Exponential exponent3 = new Exponential(2.0);
        assertEquals("e^(2.0*x)", exponent3.toString());
    }

}

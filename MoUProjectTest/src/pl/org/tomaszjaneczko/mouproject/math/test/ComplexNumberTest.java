package pl.org.tomaszjaneczko.mouproject.math.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.org.tomaszjaneczko.mouproject.math.ComplexNumber;

public class ComplexNumberTest {

    /** Tolerance for double used in simple tests. */
    private static final double TOLERANCE = 0.0;

    @Test
    public void testComplexNumbersCreation() {
        ComplexNumber complex1 = new ComplexNumber(1.0, 1.0);
        assertEquals(1.0, complex1.getRealPart(), TOLERANCE);
        assertEquals(1.0, complex1.getImaginaryPart(), TOLERANCE);

        ComplexNumber complex2 = new ComplexNumber(0.0, 1.0);
        assertEquals(0.0, complex2.getRealPart(), TOLERANCE);
        assertEquals(1.0, complex2.getImaginaryPart(), TOLERANCE);

        ComplexNumber complex3 = new ComplexNumber(1.0, 0.0);
        assertEquals(1.0, complex3.getRealPart(), TOLERANCE);
        assertEquals(0.0, complex3.getImaginaryPart(), TOLERANCE);

        ComplexNumber complex4 = new ComplexNumber(-1.0, -1.0);
        assertEquals(-1.0, complex4.getRealPart(), TOLERANCE);
        assertEquals(-1.0, complex4.getImaginaryPart(), TOLERANCE);
    }

    @Test
    public void testRenderingOfComplexNumbers() {
        ComplexNumber complex1 = new ComplexNumber(1.0, 1.0);
        assertEquals("1.0+1.0i", complex1.toString());

        ComplexNumber complex2 = new ComplexNumber(0.0, 1.0);
        assertEquals("0.0+1.0i", complex2.toString());

        ComplexNumber complex3 = new ComplexNumber(1.0, 0.0);
        assertEquals("1.0+0.0i", complex3.toString());

        ComplexNumber complex4 = new ComplexNumber(-1.0, -1.0);
        assertEquals("-1.0-1.0i", complex4.toString());

        ComplexNumber complex5 = new ComplexNumber(0.0, 0.0);
        assertEquals("0.0+0.0i", complex5.toString());
    }

    @Test
    public void testEquality() {
        ComplexNumber complex1 = new ComplexNumber(1.0, 1.0);
        ComplexNumber complex2 = new ComplexNumber(1.0, 1.0);
        ComplexNumber complex3 = new ComplexNumber(2.0, 1.0);
        ComplexNumber complex4 = new ComplexNumber(0.0, 0.0);

        assertEquals(complex1, complex2);
        assertFalse(complex1.equals(complex3));
        assertFalse(complex1.equals(complex4));
    }

}

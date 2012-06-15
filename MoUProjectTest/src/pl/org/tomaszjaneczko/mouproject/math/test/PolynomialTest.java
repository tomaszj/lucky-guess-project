package pl.org.tomaszjaneczko.mouproject.math.test;

import org.junit.Test;
import static org.junit.Assert.*;

import pl.org.tomaszjaneczko.mouproject.math.Polynomial;

public class PolynomialTest {

    @Test
    public void testDegree() {
        Polynomial polynomial1 = new Polynomial(new Double[]{ 0.0 });
        assertEquals(polynomial1.getDegree(), 0);

        Polynomial polynomial2 = new Polynomial(new Double[]{ 1.0 });
        assertEquals(polynomial2.getDegree(), 0);

        Polynomial polynomial3 = new Polynomial(new Double[]{ 1.0, 1.0 });
        assertEquals(polynomial3.getDegree(), 1);

        Polynomial polynomial4 = new Polynomial(new Double[]{ 1.0, 1.0, 1.0 });
        assertEquals(polynomial4.getDegree(), 2);

        Polynomial polynomial5 = new Polynomial(new Double[]{ 1.0, 0.0, 0.0 });
        assertEquals(polynomial5.getDegree(), 2);

        Polynomial polynomial6 = new Polynomial(new Double[]{ 0.0, 1.0, 0.0, 0.0 });
        assertEquals(polynomial6.getDegree(), 2);
    }

    @Test
    public void testCoefficientForDegree() {
        Polynomial polynomial1 = new Polynomial(new Double[]{ 0.0 });
        assertEquals(polynomial1.getCoefficientForDegree(0), new Double(0.0));

        Polynomial polynomial2 = new Polynomial(new Double[]{ 1.0 });
        assertEquals(polynomial2.getCoefficientForDegree(0), new Double(1.0));

        Polynomial polynomial3 = new Polynomial(new Double[]{ 1.0, 1.0 });
        assertEquals(polynomial3.getCoefficientForDegree(1), new Double(1.0));
        assertEquals(polynomial3.getCoefficientForDegree(0), new Double(1.0));

        Polynomial polynomial4 = new Polynomial(new Double[]{ 1.0, 1.0, 1.0 });
        assertEquals(polynomial4.getCoefficientForDegree(2), new Double(1.0));
        assertEquals(polynomial4.getCoefficientForDegree(1), new Double(1.0));
        assertEquals(polynomial4.getCoefficientForDegree(0), new Double(1.0));

        Polynomial polynomial5 = new Polynomial(new Double[]{ 1.0, 0.0, 0.0 });
        assertEquals(polynomial5.getCoefficientForDegree(2), new Double(1.0));
        assertEquals(polynomial5.getCoefficientForDegree(1), new Double(0.0));
        assertEquals(polynomial5.getCoefficientForDegree(0), new Double(0.0));

        Polynomial polynomial6 = new Polynomial(new Double[]{ 0.0, 1.0, 0.0, 0.0 });
        assertEquals(polynomial6.getCoefficientForDegree(2), new Double(1.0));
        assertEquals(polynomial6.getCoefficientForDegree(1), new Double(0.0));
        assertEquals(polynomial6.getCoefficientForDegree(0), new Double(0.0));
    }

    @Test
    public void testSingleArgumentPolynomial() {
        Polynomial polynomial1 = Polynomial.getSingleArgumentPolynomialOfDegree(1);
        assertEquals(polynomial1, new Polynomial(new Double[] {1.0, 0.0}));

        Polynomial polynomial2 = Polynomial.getSingleArgumentPolynomialOfDegree(2);
        assertEquals(polynomial2, new Polynomial(new Double[] {1.0, 0.0, 0.0}));

        Polynomial polynomial3 = Polynomial.getSingleArgumentPolynomialOfDegree(4);
        assertEquals(polynomial3, new Polynomial(new Double[] {1.0, 0.0, 0.0, 0.0, 0.0}));
    }

    @Test
    public void testRendering() {
        Polynomial polynomial1 = new Polynomial(new Double[]{ 0.0 });
        assertEquals("0.0", polynomial1.toString());

        Polynomial polynomial2 = new Polynomial(new Double[]{ 1.0 });
        assertEquals("1.0", polynomial2.toString());

        Polynomial polynomial3 = new Polynomial(new Double[]{ 1.0, 1.0 });
        assertEquals("1.0*x+1.0", polynomial3.toString());

        Polynomial polynomial4 = new Polynomial(new Double[]{ 1.0, 1.0, 1.0 });
        assertEquals("1.0*x^2+1.0*x+1.0", polynomial4.toString());

        Polynomial polynomial5 = new Polynomial(new Double[]{ 1.0, 0.0, 0.0 });
        assertEquals("1.0*x^2", polynomial5.toString());

        Polynomial polynomial6 = new Polynomial(new Double[]{ 1.0, -1.0, -1.0 });
        assertEquals("1.0*x^2-1.0*x-1.0", polynomial6.toString());

        Polynomial polynomial7 = new Polynomial(new Double[]{ -1.0, 0.0, 0.0 });
        assertEquals("-1.0*x^2", polynomial7.toString());
    }

    @Test
    public void testEquals() {
        Polynomial poly1 = new Polynomial(new Double[]{ 1.0, 1.0 });
        Polynomial poly2 = new Polynomial(new Double[]{ 1.0, 1.0 });
        Polynomial poly3 = new Polynomial(new Double[]{ -1.0, 1.0 });
        Polynomial poly4 = new Polynomial(new Double[]{ 1.0 });

        assertEquals(poly1, poly2);
        assertFalse(poly1.equals(poly3));
        assertFalse(poly1.equals(poly4));
    }

}

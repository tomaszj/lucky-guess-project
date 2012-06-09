package pl.org.tomaszjaneczko.mouproject.math.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.org.tomaszjaneczko.mouproject.math.Polynomial;
import pl.org.tomaszjaneczko.mouproject.math.SineAndCosine;

public class SineAndCosineTest {

    @Test
    public void testTrigCoefficient() {
        SineAndCosine sineAndCosine1 = new SineAndCosine(1.5, new Polynomial(
                new Double[] {1.0}), new Polynomial(new Double[] {1.0}));

        assertEquals(1.5, sineAndCosine1.getTrigCoefficient(), Double.MIN_VALUE);
    }

    @Test
    public void testNormalEquality() {

        SineAndCosine sineAndCosine1 = new SineAndCosine(1.5, new Polynomial(
                new Double[] {1.0}), new Polynomial(new Double[] {1.0}));

        SineAndCosine sineAndCosine2 = new SineAndCosine(1.5, new Polynomial(
                new Double[] {1.0}), new Polynomial(new Double[] {1.0}));

        assertEquals(sineAndCosine1, sineAndCosine1);
        assertEquals(sineAndCosine2, sineAndCosine1);

        SineAndCosine sineAndCosine3 = new SineAndCosine(1.5, new Polynomial(
                new Double[] {0.0}), new Polynomial(new Double[] {1.0}));

        SineAndCosine sineAndCosine4 = new SineAndCosine(1.5, new Polynomial(
                new Double[] {0.0}), new Polynomial(new Double[] {1.0}));

        assertEquals(sineAndCosine3, sineAndCosine3);
        assertEquals(sineAndCosine3, sineAndCosine4);
    }

    @Test
    public void testSingularEquality() {
        SineAndCosine sineAndCosine1 = new SineAndCosine(3.0, new Polynomial(
                new Double[] {0.0}), new Polynomial(new Double[] {0.0}));

        SineAndCosine sineAndCosine2 = new SineAndCosine(1.5, new Polynomial(
                new Double[] {0.0}), new Polynomial(new Double[] {0.0}));

        assertEquals(sineAndCosine1, sineAndCosine2);

        SineAndCosine sineAndCosine3 = new SineAndCosine(0.0, new Polynomial(
                new Double[] {5.0}), new Polynomial(new Double[] {1.0}));

        SineAndCosine sineAndCosine4 = new SineAndCosine(0.0, new Polynomial(
                new Double[] {6.0}), new Polynomial(new Double[] {1.0}));

        assertEquals(sineAndCosine3, sineAndCosine4);

        SineAndCosine sineAndCosine5 = SineAndCosine.getSingularSineAndCosine();

        assertEquals(sineAndCosine5, sineAndCosine5);
    }

    @Test
    public void testInequality() {
        SineAndCosine sineAndCosine1 = new SineAndCosine(1.5, new Polynomial(
                new Double[] {1.0}), new Polynomial(new Double[] {1.0}));

        SineAndCosine sineAndCosine2 = new SineAndCosine(0.0, new Polynomial(
                new Double[] {1.0}), new Polynomial(new Double[] {1.0}));

        assertFalse(sineAndCosine1.equals(sineAndCosine2));

        SineAndCosine sineAndCosine3 = new SineAndCosine(1.5, new Polynomial(
                new Double[] {1.0}), new Polynomial(new Double[] {1.0}));

        SineAndCosine sineAndCosine4 = new SineAndCosine(1.5, new Polynomial(
                new Double[] {0.0}), new Polynomial(new Double[] {1.0}));

        assertFalse(sineAndCosine3.equals(sineAndCosine4));

        SineAndCosine sineAndCosine5 = new SineAndCosine(0.0, new Polynomial(
                new Double[] {1.0}), new Polynomial(new Double[] {2.0}));

        SineAndCosine sineAndCosine6 = new SineAndCosine(0.0, new Polynomial(
                new Double[] {1.0}), new Polynomial(new Double[] {1.0}));

        assertFalse(sineAndCosine5.equals(sineAndCosine6));
    }

    @Test
    public void testToString() {
        SineAndCosine sineAndCosine1 = new SineAndCosine(1.5, new Polynomial(
                new Double[] {1.0}), new Polynomial(new Double[] {1.0}));
        assertEquals("(1.0)*sin(1.5*x)+(1.0)*cos(1.5*x)", sineAndCosine1.toString());

        SineAndCosine sineAndCosine2 = new SineAndCosine(3.0, new Polynomial(
                new Double[] {1.0}), new Polynomial(new Double[] {1.0}));
        assertEquals("(1.0)*sin(3.0*x)+(1.0)*cos(3.0*x)", sineAndCosine2.toString());

        SineAndCosine sineAndCosine3 = new SineAndCosine(1.5, new Polynomial(
                new Double[] {0.0}), new Polynomial(new Double[] {1.0}));
        assertEquals("(1.0)*cos(1.5*x)", sineAndCosine3.toString());

        SineAndCosine sineAndCosine4 = new SineAndCosine(1.5, new Polynomial(
                new Double[] {1.0}), new Polynomial(new Double[] {0.0}));
        assertEquals("(1.0)*sin(1.5*x)", sineAndCosine4.toString());

        SineAndCosine sineAndCosine5 = new SineAndCosine(1.5, new Polynomial(
                new Double[] {1.0}), new Polynomial(new Double[] {2.0, -1.0}));
        assertEquals("(1.0)*sin(1.5*x)+(2.0*x-1.0)*cos(1.5*x)", sineAndCosine5.toString());

        SineAndCosine sineAndCosine6 = new SineAndCosine(1.5, new Polynomial(
                new Double[] {1.0, 1.0}), new Polynomial(new Double[] {0.0}));
        assertEquals("(1.0*x+1.0)*sin(1.5*x)", sineAndCosine6.toString());
    }

}

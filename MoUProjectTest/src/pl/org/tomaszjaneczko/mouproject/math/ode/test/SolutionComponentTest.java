package pl.org.tomaszjaneczko.mouproject.math.ode.test;

import org.junit.Test;

import static org.junit.Assert.*;

import pl.org.tomaszjaneczko.mouproject.math.Exponential;
import pl.org.tomaszjaneczko.mouproject.math.Polynomial;
import pl.org.tomaszjaneczko.mouproject.math.SineAndCosine;
import pl.org.tomaszjaneczko.mouproject.math.ode.EquationComponent;

public class SolutionComponentTest {

    /**
     * Test for {@link pl.org.tomaszjaneczko.mouproject.math.ode.EquationComponent}.
     */
    @Test
    public void testCreation() {
        Polynomial polynomial = new Polynomial(new Double[] {1.0, 1.0});
        Exponential exponential = new Exponential(5.0);
        SineAndCosine sinAndCos = new SineAndCosine(3.0, polynomial, polynomial);
        EquationComponent component = new EquationComponent(polynomial, exponential, sinAndCos);

        assertEquals(polynomial, component.getPolynomial());
        assertEquals(exponential, component.getExponential());
        assertEquals(sinAndCos, component.getSineAndCosine());
    }

    /**
     * Test for {@link pl.org.tomaszjaneczko.mouproject.math.ode.EquationComponent}.
     */
    @Test
    public void testEquality() {
        EquationComponent component1 = getExampleSolutionComponent();
        EquationComponent component2 = getExampleSolutionComponent();

        assertEquals(component1, component1);
        assertEquals(component1, component2);
    }

    /**
     * Test for {@link pl.org.tomaszjaneczko.mouproject.math.ode.EquationComponent}.
     */
    @Test
    public void testInequality() {
        EquationComponent component1 = getExampleSolutionComponent();
        EquationComponent component2 = getDifferentSolutionComponent();

        assertFalse(component1.equals(component2));
    }

    public static EquationComponent getExampleSolutionComponent() {
        Polynomial polynomial = new Polynomial(new Double[] {1.0, 1.0});
        Exponential exponential = new Exponential(5.0);

        Polynomial trigPoly = new Polynomial(new Double[] {1.0});
        SineAndCosine sinAndCos = new SineAndCosine(3.0, trigPoly, trigPoly);
        return new EquationComponent(polynomial, exponential, sinAndCos);
    }

    public static EquationComponent getDifferentSolutionComponent() {
        Polynomial polynomial = new Polynomial(new Double[] {1.0});
        Exponential exponential = new Exponential(3.0);

        Polynomial trigPoly = new Polynomial(new Double[] {1.0});
        SineAndCosine sinAndCos = new SineAndCosine(3.0, trigPoly, trigPoly);
        return new EquationComponent(polynomial, exponential, sinAndCos);
    }

    /**
     * Test for {@link pl.org.tomaszjaneczko.mouproject.math.ode.EquationComponent}.
     */
    @Test
    public void testSingularRendering() {
        Polynomial polynomial = Polynomial.getSingularPolynomial();
        Exponential exponential = Exponential.getSingularExponential();
        SineAndCosine sinAndCos = SineAndCosine.getSingularSineAndCosine();
        EquationComponent component = new EquationComponent(polynomial, exponential, sinAndCos);

        assertEquals("1.0", component.toString());
    }

    /**
     * Test for {@link pl.org.tomaszjaneczko.mouproject.math.ode.EquationComponent}.
     */
    @Test
    public void testFullRendering() {
        EquationComponent component = getExampleSolutionComponent();

        assertEquals(
                "(1.0*x+1.0)*e^(5.0*x)*((1.0)*sin(3.0*x)+(1.0)*cos(3.0*x))",
                component.toString());

        EquationComponent component2 = getDifferentSolutionComponent();

        assertEquals("1.0*e^(3.0*x)*((1.0)*sin(3.0*x)+(1.0)*cos(3.0*x))", component2.toString());

    }


}

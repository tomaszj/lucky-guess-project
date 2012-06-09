package pl.org.tomaszjaneczko.mouproject.math.ode.test;

import org.junit.Test;

import static org.junit.Assert.*;

import pl.org.tomaszjaneczko.mouproject.math.Exponential;
import pl.org.tomaszjaneczko.mouproject.math.Polynomial;
import pl.org.tomaszjaneczko.mouproject.math.SineAndCosine;
import pl.org.tomaszjaneczko.mouproject.math.ode.SolutionComponent;

public class SolutionComponentTest {

    /**
     * Test for {@link pl.org.tomaszjaneczko.mouproject.math.ode.SolutionComponent}.
     */
    @Test
    public void testEquality() {
        SolutionComponent component1 = getExampleSolutionComponent();
        SolutionComponent component2 = getExampleSolutionComponent();

        assertEquals(component1, component1);
        assertEquals(component1, component2);
    }

    /**
     * Test for {@link pl.org.tomaszjaneczko.mouproject.math.ode.SolutionComponent}.
     */
    @Test
    public void testInequality() {
        SolutionComponent component1 = getExampleSolutionComponent();
        SolutionComponent component2 = getDifferentSolutionComponent();

        assertFalse(component1.equals(component2));
    }

    public static SolutionComponent getExampleSolutionComponent() {
        Polynomial polynomial = new Polynomial(new Double[] {1.0, 1.0});
        Exponential exponential = new Exponential(5.0);
        SineAndCosine sinAndCos = new SineAndCosine(3.0, polynomial, polynomial);
        return new SolutionComponent(polynomial, exponential, sinAndCos);
    }

    public static SolutionComponent getDifferentSolutionComponent() {
        Polynomial polynomial = new Polynomial(new Double[] {1.0, 1.0});
        Exponential exponential = new Exponential(3.0);
        SineAndCosine sinAndCos = new SineAndCosine(3.0, polynomial, polynomial);
        return new SolutionComponent(polynomial, exponential, sinAndCos);
    }

}

/**
 * 
 */
package pl.org.tomaszjaneczko.mouproject.math.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.org.tomaszjaneczko.mouproject.math.Exponential;
import pl.org.tomaszjaneczko.mouproject.math.ParametrisedComponent;
import pl.org.tomaszjaneczko.mouproject.math.ParametrisedPolynomial;

/**
 * @author tomaszj
 *
 */
public class ParametrisedComponentTest {

    /**
     * Test method for {@link pl.org.tomaszjaneczko.mouproject.math.ParametrisedComponent#differentiate()}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFailsDifferentiation() {
        ParametrisedComponent component = new ParametrisedComponent();

        component.differentiate();
        fail("Should have failed.");
    }

    /**
     * Test method for {@link pl.org.tomaszjaneczko.mouproject.math.ParametrisedComponent#differentiate()}.
     */
    @Test
    public void testDifferentiationSucceeds() {
        ParametrisedComponent component = new ParametrisedComponent();
        component.setPolynomial(new ParametrisedPolynomial(new String[] {"A"}));
        component.setExponential(new Exponential(1.0));

        try {
            component.differentiate();
            // Do nothing, should have reached this point
        } catch (IllegalArgumentException e) {
            fail("Should have succeeded.");
        }
    }

    /**
     * Test method for {@link pl.org.tomaszjaneczko.mouproject.math.ParametrisedComponent#add()}.
     */
    @Test
    public void testAddition() {
        ParametrisedComponent component = new ParametrisedComponent();
        component.setPolynomial(new ParametrisedPolynomial(new String[] {"A", "B"}));
        component.setExponential(new Exponential(1.0));

        ParametrisedComponent component2 = new ParametrisedComponent();
        component2.setPolynomial(new ParametrisedPolynomial(new String[] {"A", "B"}));
        component2.setExponential(new Exponential(1.0));

        ParametrisedComponent resultComponent = component.add(component2);
        ParametrisedPolynomial resultPoly = resultComponent.getPolynomial();


        assertArrayEquals(new Double[] {2.0, 0.0}, resultPoly.getParamValuesForDegreeAsArray(1));
        assertArrayEquals(new Double[] {0.0, 2.0}, resultPoly.getParamValuesForDegreeAsArray(0));

    }

}

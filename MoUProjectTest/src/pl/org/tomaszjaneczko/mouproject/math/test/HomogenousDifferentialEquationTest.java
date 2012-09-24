package pl.org.tomaszjaneczko.mouproject.math.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.org.tomaszjaneczko.mouproject.math.Polynomial;
import pl.org.tomaszjaneczko.mouproject.math.ode.HomogenousDifferentialEquation;

public class HomogenousDifferentialEquationTest {

    @Test
    public void testCharacteristicEquation() {
        HomogenousDifferentialEquation equation1 = new HomogenousDifferentialEquation(
                new Double[] {1.0}, 1.0);

        assertEquals(equation1.getCharacteristicEquation(), new Polynomial(new Double[] {1.0, 1.0}));

        HomogenousDifferentialEquation equation2 = new HomogenousDifferentialEquation(
                new Double[] {1.0, 1.0}, 1.0);
        assertEquals(equation2.getCharacteristicEquation(), new Polynomial(new Double[] {1.0, 1.0, 1.0}));

        HomogenousDifferentialEquation equation3 = new HomogenousDifferentialEquation(
                new Double[] {1.0, -1.0, 1.0}, 1.0);
        assertEquals(equation3.getCharacteristicEquation(), new Polynomial(new Double[] {1.0, -1.0, 1.0, 1.0}));

        HomogenousDifferentialEquation equation4 = new HomogenousDifferentialEquation(
                new Double[] {-1.0}, -1.0);
        assertEquals(equation4.getCharacteristicEquation(), new Polynomial(new Double[] {-1.0, -1.0}));
    }

    @Test
    public void testGetDegree() {
        HomogenousDifferentialEquation equation1 = new HomogenousDifferentialEquation(
                new Double[] {1.0}, 1.0);

        assertTrue(equation1.getDegree() == 1);

        HomogenousDifferentialEquation equation2 = new HomogenousDifferentialEquation(
                new Double[] {1.0, 1.0}, 1.0);
        assertTrue(equation2.getDegree() == 2);

        HomogenousDifferentialEquation equation3 = new HomogenousDifferentialEquation(
                new Double[] {1.0, -1.0, 1.0}, 1.0);
        assertTrue(equation3.getDegree() == 3);

    }

    @Test
    public void testGetCoefficientForDegree() {
        HomogenousDifferentialEquation equation = new HomogenousDifferentialEquation(
                new Double[] {1.0, -1.0, 2.0}, -2.0);

        assertTrue(equation.getDegree() == 3);
        assertEquals(equation.getCoefficientForDegree(3), 1.0, 0.0);
        assertEquals(equation.getCoefficientForDegree(2), -1.0, 0.0);
        assertEquals(equation.getCoefficientForDegree(1), 2.0, 0.0);
        assertEquals(equation.getCoefficientForDegree(0), -2.0, 0.0);


    }

}

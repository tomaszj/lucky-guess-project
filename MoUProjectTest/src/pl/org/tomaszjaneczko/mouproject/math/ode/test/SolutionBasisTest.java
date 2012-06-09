package pl.org.tomaszjaneczko.mouproject.math.ode.test;

import org.junit.Test;

import pl.org.tomaszjaneczko.mouproject.math.ComplexNumber;
import pl.org.tomaszjaneczko.mouproject.math.ode.SolutionBasis;

public class SolutionBasisTest {

    @Test
    public void testSimpleRoots() {
        ComplexNumber[] roots = new ComplexNumber[] { new ComplexNumber(0.0, 0.0) };

        SolutionBasis basis = new SolutionBasis(roots);

        //        SolutionComponent component = new SolutionComponent(poly, exponent, sinAndCos)
    }

}

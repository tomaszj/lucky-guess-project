package pl.org.tomaszjaneczko.mouproject.math.ode.test;

import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;

import pl.org.tomaszjaneczko.mouproject.math.ComplexNumber;
import pl.org.tomaszjaneczko.mouproject.math.Exponential;
import pl.org.tomaszjaneczko.mouproject.math.Polynomial;
import pl.org.tomaszjaneczko.mouproject.math.SineAndCosine;
import pl.org.tomaszjaneczko.mouproject.math.ode.SolutionBasis;
import pl.org.tomaszjaneczko.mouproject.math.ode.SolutionComponent;

public class SolutionBasisTest {

    @Test
    public void testSimpleRoots() {
        {
            ComplexNumber[] roots = new ComplexNumber[] { new ComplexNumber(0.0, 0.0) };

            SolutionBasis basis = new SolutionBasis(roots);
            Set<SolutionComponent> solutionComponents = basis.getSolutionComponents();

            SolutionComponent component = new SolutionComponent(
                    Polynomial.getSingularPolynomial(),
                    Exponential.getSingularExponential(),
                    SineAndCosine.getSingularSineAndCosine());

            boolean isInSet = solutionComponents.contains(component);

            assertTrue(isInSet);
        }

        {
            // Double 0.0 root
            ComplexNumber[] roots = new ComplexNumber[] {
                    new ComplexNumber(0.0, 0.0),
                    new ComplexNumber(0.0, 0.0)
            };

            SolutionBasis basis = new SolutionBasis(roots);
            Set<SolutionComponent> solutionComponents = basis.getSolutionComponents();

            SolutionComponent component1 = new SolutionComponent(
                    Polynomial.getSingularPolynomial(),
                    Exponential.getSingularExponential(),
                    SineAndCosine.getSingularSineAndCosine());

            SolutionComponent component2 = new SolutionComponent(
                    new Polynomial(new Double[] {1.0, 0.0}),
                    Exponential.getSingularExponential(),
                    SineAndCosine.getSingularSineAndCosine());

            assertTrue(solutionComponents.contains(component1));
            assertTrue(solutionComponents.contains(component2));
        }

        {
            // Triple 0.0 root
            ComplexNumber[] roots = new ComplexNumber[] {
                    new ComplexNumber(0.0, 0.0),
                    new ComplexNumber(0.0, 0.0),
                    new ComplexNumber(0.0, 0.0)
            };

            SolutionBasis basis = new SolutionBasis(roots);
            Set<SolutionComponent> solutionComponents = basis.getSolutionComponents();

            SolutionComponent component1 = new SolutionComponent(
                    Polynomial.getSingularPolynomial(),
                    Exponential.getSingularExponential(),
                    SineAndCosine.getSingularSineAndCosine());

            SolutionComponent component2 = new SolutionComponent(
                    new Polynomial(new Double[] {1.0, 0.0}),
                    Exponential.getSingularExponential(),
                    SineAndCosine.getSingularSineAndCosine());

            SolutionComponent component3 = new SolutionComponent(
                    new Polynomial(new Double[] {1.0, 0.0, 0.0}),
                    Exponential.getSingularExponential(),
                    SineAndCosine.getSingularSineAndCosine());

            assertTrue(solutionComponents.contains(component1));
            assertTrue(solutionComponents.contains(component2));
            assertTrue(solutionComponents.contains(component3));
        }


    }

}

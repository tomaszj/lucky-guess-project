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

    @Test
    public void testExponentialRoots() {
        {
            ComplexNumber[] roots = new ComplexNumber[] { new ComplexNumber(1.0, 0.0) };

            SolutionBasis basis = new SolutionBasis(roots);
            Set<SolutionComponent> solutionComponents = basis.getSolutionComponents();

            SolutionComponent component = new SolutionComponent(
                    Polynomial.getSingularPolynomial(),
                    new Exponential(1.0),
                    SineAndCosine.getSingularSineAndCosine());

            boolean isInSet = solutionComponents.contains(component);

            assertTrue(isInSet);
        }

        {
            // Double 1.0 root
            ComplexNumber[] roots = new ComplexNumber[] {
                    new ComplexNumber(1.0, 0.0),
                    new ComplexNumber(1.0, 0.0)
            };

            SolutionBasis basis = new SolutionBasis(roots);
            Set<SolutionComponent> solutionComponents = basis.getSolutionComponents();

            SolutionComponent component1 = new SolutionComponent(
                    Polynomial.getSingularPolynomial(),
                    new Exponential(1.0),
                    SineAndCosine.getSingularSineAndCosine());

            SolutionComponent component2 = new SolutionComponent(
                    new Polynomial(new Double[] {1.0, 0.0}),
                    new Exponential(1.0),
                    SineAndCosine.getSingularSineAndCosine());

            assertTrue(solutionComponents.contains(component1));
            assertTrue(solutionComponents.contains(component2));
        }

        {
            // Triple 1.0 root
            ComplexNumber[] roots = new ComplexNumber[] {
                    new ComplexNumber(1.0, 0.0),
                    new ComplexNumber(1.0, 0.0),
                    new ComplexNumber(1.0, 0.0)
            };

            SolutionBasis basis = new SolutionBasis(roots);
            Set<SolutionComponent> solutionComponents = basis.getSolutionComponents();

            SolutionComponent component1 = new SolutionComponent(
                    Polynomial.getSingularPolynomial(),
                    new Exponential(1.0),
                    SineAndCosine.getSingularSineAndCosine());

            SolutionComponent component2 = new SolutionComponent(
                    new Polynomial(new Double[] {1.0, 0.0}),
                    new Exponential(1.0),
                    SineAndCosine.getSingularSineAndCosine());

            SolutionComponent component3 = new SolutionComponent(
                    new Polynomial(new Double[] {1.0, 0.0, 0.0}),
                    new Exponential(1.0),
                    SineAndCosine.getSingularSineAndCosine());

            assertTrue(solutionComponents.contains(component1));
            assertTrue(solutionComponents.contains(component2));
            assertTrue(solutionComponents.contains(component3));
        }
    }

    public void testSineRoots() {
        {
            // Conjugate +- 1.0i root
            ComplexNumber[] roots = new ComplexNumber[] {
                    new ComplexNumber(0.0, 1.0),
                    new ComplexNumber(0.0, -1.0)
            };

            SolutionBasis basis = new SolutionBasis(roots);
            Set<SolutionComponent> solutionComponents = basis.getSolutionComponents();

            SolutionComponent component1 = new SolutionComponent(
                    Polynomial.getSingularPolynomial(),
                    Exponential.getSingularExponential(),
                    new SineAndCosine(
                            1.0, Polynomial.getSingularPolynomial(),
                            Polynomial.getSingularPolynomial()));

            assertTrue(solutionComponents.contains(component1));
        }

        {
            // Double Conjugate +- 1.0i root
            ComplexNumber[] roots = new ComplexNumber[] {
                    new ComplexNumber(0.0, 1.0),
                    new ComplexNumber(0.0, -1.0),
                    new ComplexNumber(0.0, 1.0),
                    new ComplexNumber(0.0, -1.0)
            };

            SolutionBasis basis = new SolutionBasis(roots);
            Set<SolutionComponent> solutionComponents = basis.getSolutionComponents();

            SolutionComponent component1 = new SolutionComponent(
                    Polynomial.getSingularPolynomial(),
                    Exponential.getSingularExponential(),
                    new SineAndCosine(
                            1.0, Polynomial.getSingularPolynomial(),
                            Polynomial.getSingularPolynomial()));

            SolutionComponent component2 = new SolutionComponent(
                    new Polynomial(new Double[] {1.0, 0.0}),
                    Exponential.getSingularExponential(),
                    new SineAndCosine(
                            1.0, Polynomial.getSingularPolynomial(),
                            Polynomial.getSingularPolynomial()));

            assertTrue(solutionComponents.contains(component1));
            assertTrue(solutionComponents.contains(component2));
        }

    }

    @Test
    public void testFullRoots() {
        ComplexNumber[] roots = new ComplexNumber[] {
                new ComplexNumber(-1.0, 1.0),
                new ComplexNumber(-1.0, -1.0),
                new ComplexNumber(-1.0, 1.0),
                new ComplexNumber(-1.0, -1.0),
        };

        SolutionBasis basis = new SolutionBasis(roots);
        Set<SolutionComponent> solutionComponents = basis.getSolutionComponents();

        SolutionComponent component1 = new SolutionComponent(
                Polynomial.getSingularPolynomial(),
                new Exponential(-1.0),
                new SineAndCosine(
                        1.0, Polynomial.getSingularPolynomial(),
                        Polynomial.getZeroPolynomial()));

        SolutionComponent component2 = new SolutionComponent(
                Polynomial.getSingularPolynomial(),
                new Exponential(-1.0),
                new SineAndCosine(
                        1.0, Polynomial.getZeroPolynomial(),
                        Polynomial.getSingularPolynomial()));

        SolutionComponent component3 = new SolutionComponent(
                new Polynomial(new Double[] {1.0, 0.0}),
                new Exponential(-1.0),
                new SineAndCosine(
                        1.0, Polynomial.getSingularPolynomial(),
                        Polynomial.getZeroPolynomial()));

        SolutionComponent component4 = new SolutionComponent(
                new Polynomial(new Double[] {1.0, 0.0}),
                new Exponential(-1.0),
                new SineAndCosine(
                        1.0, Polynomial.getZeroPolynomial(),
                        Polynomial.getSingularPolynomial()));

        assertTrue(solutionComponents.contains(component1));
        assertTrue(solutionComponents.contains(component2));
        assertTrue(solutionComponents.contains(component3));
        assertTrue(solutionComponents.contains(component4));
    }

}

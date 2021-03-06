/**
 * 
 */
package pl.org.tomaszjaneczko.mouproject.math.polysolver.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.org.tomaszjaneczko.mouproject.math.Polynomial;
import pl.org.tomaszjaneczko.mouproject.math.polysolvers.BinomialSolver;
import pl.org.tomaszjaneczko.mouproject.math.polysolvers.IPolynomialSolver;
import pl.org.tomaszjaneczko.mouproject.math.polysolvers.PolynomialSolverFactory;
import pl.org.tomaszjaneczko.mouproject.math.polysolvers.QuadraticSolver;

/**
 * @author tomaszj
 *
 */
public class PolynomialSolverFactoryTest {

    /**
     * Test method for {@link pl.org.tomaszjaneczko.mouproject.math.polysolvers.PolynomialSolverFactory#getSolverForPolynomial(pl.org.tomaszjaneczko.mouproject.math.Polynomial)}.
     */
    @Test
    public void testGetSolverForBinomial() {
        // Test if factory returns a binomial solver for a binomial
        Polynomial binomial = new Polynomial(new Double[] {1.0, 1.0});

        IPolynomialSolver solver1 = PolynomialSolverFactory.getSolverForPolynomial(binomial);
        assertTrue(solver1 instanceof BinomialSolver);
    }

    /**
     * Test method for {@link pl.org.tomaszjaneczko.mouproject.math.polysolvers.PolynomialSolverFactory#getSolverForPolynomial(pl.org.tomaszjaneczko.mouproject.math.Polynomial)}.
     */
    @Test
    public void testGetSolverForQuadraticEqn() {
        // Test if factory returns a binomial solver for a binomial
        Polynomial quadratic = new Polynomial(new Double[] {1.0, 1.0, 1.0});

        IPolynomialSolver solver1 = PolynomialSolverFactory.getSolverForPolynomial(quadratic);
        assertTrue(solver1 instanceof QuadraticSolver);
    }



}

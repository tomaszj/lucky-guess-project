/**
 * 
 */
package pl.org.tomaszjaneczko.mouproject.math.polysolver.test;

import org.junit.Test;

import static org.junit.Assert.*;

import pl.org.tomaszjaneczko.mouproject.math.ComplexNumber;
import pl.org.tomaszjaneczko.mouproject.math.polysolvers.BinomialSolver;

/**
 * @author tomaszj
 *
 */
public class BinomialSolverTest {

    /**
     * Test method for {@link pl.org.tomaszjaneczko.mouproject.math.polysolvers.BinomialSolver#findRoots()}.
     */
    @Test
    public void testFindRoots() {
        BinomialSolver solver = new BinomialSolver(1, -1);
        ComplexNumber expectedResult = new ComplexNumber(1.0, 0.0);
        assertEquals(expectedResult, solver.findRoots()[0]);

        BinomialSolver solver2 = new BinomialSolver(1, 1);
        ComplexNumber expectedResult2 = new ComplexNumber(-1.0, 0.0);
        assertEquals(expectedResult2, solver2.findRoots()[0]);

        BinomialSolver solver3 = new BinomialSolver(2, -1);
        ComplexNumber expectedResult3 = new ComplexNumber(0.5, 0.0);
        assertEquals(expectedResult3, solver3.findRoots()[0]);

        BinomialSolver solver4 = new BinomialSolver(2, 0);
        ComplexNumber expectedResult4 = new ComplexNumber(0.0, 0.0);
        assertEquals(expectedResult4, solver4.findRoots()[0]);

        try {
            BinomialSolver solver5 = new BinomialSolver(0, 0);
            solver5.findRoots();
            fail("It should have failed");
        } catch (IllegalArgumentException e) {
            // If reaches this point, it's fine
        } catch (Exception e) {
            fail("It should have thrown IllegalArgumentException");
        }

    }

}

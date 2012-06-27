package pl.org.tomaszjaneczko.mouproject.math.polysolver.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.org.tomaszjaneczko.mouproject.math.ComplexNumber;
import pl.org.tomaszjaneczko.mouproject.math.polysolvers.QuadraticSolver;

public class QuadraticSolverTest {

    /**
     * Test method for {@link pl.org.tomaszjaneczko.mouproject.math.polysolvers.BinomialSolver#findRoots()}.
     */
    @Test
    public void testSimpleRoots() {
        // Test simple cases
        QuadraticSolver solver = new QuadraticSolver(1, -2, 1);
        ComplexNumber expectedResult = new ComplexNumber(1.0, 0.0);
        assertEquals(expectedResult, solver.findRoots()[0]);
        assertEquals(expectedResult, solver.findRoots()[1]);

        QuadraticSolver solver2 = new QuadraticSolver(1, 0, 0);
        ComplexNumber expectedResult2 = new ComplexNumber(0.0, 0.0);
        assertEquals(expectedResult2, solver2.findRoots()[0]);
        assertEquals(expectedResult2, solver2.findRoots()[1]);

        QuadraticSolver solver3 = new QuadraticSolver(1, -4, 4);
        ComplexNumber expectedResult3 = new ComplexNumber(2.0, 0.0);
        assertEquals(expectedResult3, solver3.findRoots()[0]);
        assertEquals(expectedResult3, solver3.findRoots()[1]);
    }

    /**
     * Test method for {@link pl.org.tomaszjaneczko.mouproject.math.polysolvers.BinomialSolver#findRoots()}.
     */
    @Test
    public void testRootsWithNonZeroDelta() {


        // Test full cases (delta != 0)
        QuadraticSolver solver4 = new QuadraticSolver(1, 0, -1);
        ComplexNumber expectedResult4_1 = new ComplexNumber(-1.0, 0.0);
        ComplexNumber expectedResult4_2 = new ComplexNumber(-1.0, 0.0);
        ComplexNumber[] roots4 = solver4.findRoots();

        assertTrue(isAResultPresentInSet(expectedResult4_1, roots4));
        assertTrue(isAResultPresentInSet(expectedResult4_2, roots4));

        QuadraticSolver solver5 = new QuadraticSolver(1, 3, 2);
        ComplexNumber expectedResult5_1 = new ComplexNumber(-1.0, 0.0);
        ComplexNumber expectedResult5_2 = new ComplexNumber(-2.0, 0.0);
        ComplexNumber[] roots5 = solver5.findRoots();

        assertTrue(isAResultPresentInSet(expectedResult5_1, roots5));
        assertTrue(isAResultPresentInSet(expectedResult5_2, roots5));
    }

    /**
     * Test method for {@link pl.org.tomaszjaneczko.mouproject.math.polysolvers.BinomialSolver#findRoots()}.
     */
    @Test
    public void testRootsWithComplexResults() {


        // Test full cases (delta != 0)
        QuadraticSolver solver4 = new QuadraticSolver(1, 0, 1);
        ComplexNumber expectedResult4_1 = new ComplexNumber(0.0, 1.0);
        ComplexNumber expectedResult4_2 = new ComplexNumber(0.0, -1.0);
        ComplexNumber[] roots4 = solver4.findRoots();

        assertTrue(isAResultPresentInSet(expectedResult4_1, roots4));
        assertTrue(isAResultPresentInSet(expectedResult4_2, roots4));

        QuadraticSolver solver5 = new QuadraticSolver(1, 1, 0.5);
        ComplexNumber expectedResult5_1 = new ComplexNumber(-0.5, 0.5);
        ComplexNumber expectedResult5_2 = new ComplexNumber(-0.5, -0.5);
        ComplexNumber[] roots5 = solver5.findRoots();

        assertTrue(isAResultPresentInSet(expectedResult5_1, roots5));
        assertTrue(isAResultPresentInSet(expectedResult5_2, roots5));
    }

    /*
     * Test method for {@link pl.org.tomaszjaneczko.mouproject.math.polysolvers.BinomialSolver#findRoots()}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRootsWithFailingCoefficients() {
        QuadraticSolver solver = new QuadraticSolver(0, 0, 0);
        solver.findRoots();
    }

    private static boolean isAResultPresentInSet(final ComplexNumber result, final ComplexNumber[] resultSet) {
        for (ComplexNumber resultCase : resultSet) {
            if (resultCase.equals(result)) {
                return true;
            }
        }

        return false;
    }

}

/**
 *
 */
package pl.org.tomaszjaneczko.mouproject.math.polysolvers;

import pl.org.tomaszjaneczko.mouproject.math.Polynomial;

/**
 * Class used as a factory for PolynomialSolvers.
 * @author tomaszj
 *
 */
public abstract class PolynomialSolverFactory {

    /** Factory method returning an appropriate solver for given polynomial.
     * @param poly Polynomial to be solved.
     * @return solver for a polynomial
     */
    public static IPolynomialSolver getSolverForPolynomial(final Polynomial poly) {
        int degreeOfPoly = poly.getDegree();

        if (degreeOfPoly == 0) {
            throw new IllegalArgumentException("Can't solve a singular case");
        } else if (degreeOfPoly == 1) {
            double a = poly.getCoefficient(0);
            double b = poly.getCoefficient(1);
            return new BinomialSolver(a, b);
        } else {
            throw new IllegalArgumentException("Not yet implemented for higher order polynomials.");
        }
    }
}

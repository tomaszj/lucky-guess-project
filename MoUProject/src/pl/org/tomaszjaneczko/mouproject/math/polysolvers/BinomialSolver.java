/**
 *
 */
package pl.org.tomaszjaneczko.mouproject.math.polysolvers;

import pl.org.tomaszjaneczko.mouproject.math.ComplexNumber;

/**
 * Class used to solve binomials of form a*x+b = 0.
 * @author tomaszj
 *
 */
public class BinomialSolver implements IPolynomialSolver {

    /** First coefficient of a binomial. */
    private double aCoeff;

    /** Second coefficient of a binomial. */
    private double bCoeff;

    /**
     * Default constructor used to set up coefficients.
     * @param a first coefficient of a binomial
     * @param b second coefficient of a binomial
     */
    public BinomialSolver(final double a, final double b) {
        if (a == 0.0) {
            throw new IllegalArgumentException("The coefficient by 'a' can't be equal to 0.0");
        }
        aCoeff = a;
        bCoeff = b;
    }

    /* (non-Javadoc)
     * @see pl.org.tomaszjaneczko.mouproject.math.polysolvers.IPolynomialSolver#findRoots()
     */
    @Override
    public final ComplexNumber[] findRoots() {
        double result = -bCoeff / aCoeff;
        return new ComplexNumber[] {new ComplexNumber(result, 0.0) };
    }

}

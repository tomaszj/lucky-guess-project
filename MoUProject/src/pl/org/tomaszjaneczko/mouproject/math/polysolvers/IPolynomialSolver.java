/**
 *
 */
package pl.org.tomaszjaneczko.mouproject.math.polysolvers;

import pl.org.tomaszjaneczko.mouproject.math.ComplexNumber;

/**
 * Interface for polynomial root finders.
 * @author tomaszj
 *
 */
public interface IPolynomialSolver {

    /**
     * Method finds roots of a polynomial in form of complex numbers.
     * @return array of complex values.
     */
    ComplexNumber[] findRoots();
}

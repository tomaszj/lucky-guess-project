/**
 * Method of Undetermined Coefficients Project
 *
 * Created by Tomasz Janeczko 2012
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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

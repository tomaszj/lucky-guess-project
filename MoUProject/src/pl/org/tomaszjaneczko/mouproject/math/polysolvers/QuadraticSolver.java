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
 * @author tomaszj
 *
 */
public class QuadraticSolver implements IPolynomialSolver {

    /** x^2 coefficient of a binomial. */
    private double aCoeff;

    /** x coefficient of a binomial. */
    private double bCoeff;

    /** Constant coefficient of a binomial. */
    private double cCoeff;

    /**
     * Default constructor setting all coefficients of a quadratic equation to
     * be solved.
     *
     * @param a
     *            x^2 coefficient of a binomial
     * @param b
     *            x coefficient of a binomial
     * @param c
     *            constant coefficient of a binomial
     */
    public QuadraticSolver(final double a, final double b, final double c) {
        if (a == 0.0) {
            throw new IllegalArgumentException("First argument can't be 0.0");
        }

        aCoeff = a;
        bCoeff = b;
        cCoeff = c;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.org.tomaszjaneczko.mouproject.math.polysolvers.IPolynomialSolver#findRoots
     * ()
     */
    @Override
    public final ComplexNumber[] findRoots() {
        double delta = calculateDelta();

        if (delta > 0.0) {
            double deltaSquareRoot = Math.sqrt(delta);
            double firstRoot = (-bCoeff + deltaSquareRoot) / (2 * aCoeff);
            double secondRoot = (-bCoeff - deltaSquareRoot) / (2 * aCoeff);

            return new ComplexNumber[] {
                    new ComplexNumber(firstRoot, 0),
                    new ComplexNumber(secondRoot, 0)
            };

        } else if (delta == 0.0) {
            double doubleRoot = -bCoeff / (2 * aCoeff);

            return new ComplexNumber[] {
                    new ComplexNumber(doubleRoot, 0),
                    new ComplexNumber(doubleRoot, 0)
            };

        } else {
            // Calculate the imaginary delta
            double deltaSquareRoot = Math.sqrt(-delta);

            double realPart = -bCoeff / (2 * aCoeff);
            double imagPart = deltaSquareRoot / (2 * aCoeff);

            ComplexNumber firstSolution = new ComplexNumber(realPart, imagPart);
            ComplexNumber secondSolution = new ComplexNumber(realPart, -imagPart);

            return new ComplexNumber[] {
                    firstSolution,
                    secondSolution
            };
        }
    }

    /**
     * Method used to calculate the determinant.
     * @return determinant of a quadratic equation
     */
    private double calculateDelta() {
        return bCoeff * bCoeff - 4 * aCoeff * cCoeff;
    }

}

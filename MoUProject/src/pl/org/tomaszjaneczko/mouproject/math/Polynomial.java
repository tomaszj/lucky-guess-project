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
 *
 */
package pl.org.tomaszjaneczko.mouproject.math;

import java.util.Arrays;

/**
 * Class representing polynomials for use with the MoU.
 * @author tomaszj
 *
 */
public class Polynomial {

    /** Coefficients of a polynomial. */
    private Double[] coefficients;

    /**
     * Public constructor which uses stripped coefficients.
     * @param coeffs of a polynomial
     */
    public Polynomial(final Double[] coeffs) {

        coefficients = getReducedCoeffs(coeffs);
    }

    /**
     * Convenience method, which returns a zero polynomial (y = 0).
     * @return zero polynomial
     */
    public static Polynomial getZeroPolynomial() {
        return new Polynomial(new Double[] {0.0});
    }

    /**
     * Convenience method, which returns a singular polynomial (y = 1).
     * @return singular polynomial
     */
    public static Polynomial getSingularPolynomial() {
        return new Polynomial(new Double[] {1.0});
    }

    /**
     * Convenience method, which returns a single argument polynomial.
     * Example:
     * For degree = 1: x
     * For degree = 2: x^2
     * For degree = 4: x^4
     * @param degree of target polynomial
     * @return single argument polynomial of a given degree
     */
    public static Polynomial getSingleArgumentPolynomialOfDegree(final int degree) {
        Double[] coeffs = new Double[degree + 1];
        Arrays.fill(coeffs, 0.0);

        coeffs[0] = 1.0;

        return new Polynomial(coeffs);
    }

    /**
     * Method returns a coefficient at given index.
     * @param index of a coefficient
     * @return coefficient value
     */
    public final Double getCoefficient(final int index) {
        return coefficients[index];
    }

    /**
     * Method returns a coefficient for given degree.
     * @param degree of a coefficient
     * @return coefficient value
     */
    public final Double getCoefficientForDegree(final int degree) {
        return coefficients[getDegree() - degree];
    }

    /**
     * Method return the degree of a polynomial.
     * @return degree of a polynomial
     */
    public final int getDegree() {
        if (coefficients.length >= 1) {
            return coefficients.length - 1;
        } else {
            return 0;
        }
    }

    @Override
    public final String toString() {
        PolynomialRenderer renderer = new PolynomialRenderer(this);
        return renderer.render();
    }

    @Override
    public final boolean equals(final Object obj) {
        return Arrays.equals(coefficients, ((Polynomial) obj).coefficients);
    }

    @Override
    public final int hashCode() {
        return 0;
    }

    /**
     * Method returns the simplest array for given coefficients.
     * It's task is to strip following zeros, which provide no particular information.
     * @param coeffs Array of coefficients to be stripped
     * @return normalised coefficients
     */
    private Double[] getReducedCoeffs(final Double[] coeffs) {
        int nonzeroIndex = 0;

        while (nonzeroIndex < coeffs.length) {
            double coefficient = coeffs[nonzeroIndex];
            if (coefficient != 0) {
                break;
            }

            nonzeroIndex++;
        }

        if (nonzeroIndex < coeffs.length) {
            return Arrays.copyOfRange(coeffs, nonzeroIndex, coeffs.length);
        } else {
            return new Double[] {0.0};
        }
    }

    /**
     * Class used for rendering the polynomials to a string.
     * @author tomaszj
     *
     */
    private class PolynomialRenderer {
        /** Polynomial to be rendered. */
        private Polynomial polynomial;

        /**
         * Default constructor.
         * @param poly polynomial
         */
        public PolynomialRenderer(final Polynomial poly) {
            polynomial = poly;
        }

        /**
         * Method renders a single (unsigned) component without a coefficient.
         * @param value The value of a coefficient
         * @param index Its index in the array
         * @return rendered coefficient
         */
        private String renderCoefficient(final double value, final int index) {
            // Calculate the degree at current polynomial index
            int degreeOfPoly = polynomial.getDegree();
            int degreeAtCurrentIndex = degreeOfPoly - index;
            if (degreeAtCurrentIndex < 0) {
                degreeAtCurrentIndex = 0;
            }

            String result;

            if (value == 0.0 && index > 0) {
                // Zero coefficient and not a first element
                return "";
            }

            if (degreeAtCurrentIndex > 0) {
                if (degreeAtCurrentIndex > 1) {
                    // Higher-degree component
                    result = String.valueOf(value) + "*x^" + String.valueOf(degreeAtCurrentIndex);
                } else {
                    // First-degree component
                    result = String.valueOf(value) + "*x";
                }
            } else {
                // Constant component or first (and only) zero
                result = String.valueOf(value);
            }

            return result;
        }

        /**
         * Method renders a signed coefficient.
         * @param value The value of a coefficient
         * @param index Its index in the array
         * @return rendered coefficient
         */
        private String renderSignedCoefficient(final double value, final int index) {

            String renderedCoefficient = renderCoefficient(Math.abs(value), index);

            if (renderedCoefficient.length() == 0) {
                return "";
            }

            if (index == 0) {
                // First coefficient
                if (value < 0) {
                    return "-" + renderedCoefficient;
                } else {
                    return renderedCoefficient;
                }
            } else {
                // Consecutive coefficient
                if (value < 0) {
                    return "-" + renderedCoefficient;
                } else {
                    return "+" + renderedCoefficient;
                }
            }
        }

        /**
         * Method which renders the whole polynomial.
         * @return rendered polynomial
         */
        public String render() {
            StringBuilder stringBuilder = new StringBuilder();
            int degree = polynomial.getDegree();
            for (int index = 0; index < degree + 1; index++) {
                stringBuilder.append(renderSignedCoefficient(polynomial.getCoefficient(index), index));
            }

            return stringBuilder.toString();
        }
    }
}

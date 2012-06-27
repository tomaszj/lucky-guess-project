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
 * Class representing a component consisting of a polynomial, exponential and trig combination with polynomials.
 *
 * Form: polynomial * exponential * (sinePolynomial * sinus + cosinePolynomial * cosinus)
 * @author tomaszj
 *
 */
public class ParametrisedComponent {

    /** Parametrised polynomial. */
    private ParametrisedPolynomial polynomial;

    /** Exponential. */
    private Exponential exponential;


    /**
     * Method validates if necessary data was set.
     * @return true if necessary fields were set
     */
    private boolean validateData() {
        return polynomial != null && exponential != null;
    }

    /**
     * Method returns a derivative of a component.
     * @return differentiated parametrised component
     */
    public final ParametrisedComponent differentiate() {
        boolean dataValid = validateData();
        if (!dataValid) {
            throw new IllegalArgumentException("Required fields were not set!");
        }

        // left derivative * e^(ax) + left * a * e^(ax)
        ParametrisedComponent differentiatedComponent = new ParametrisedComponent();
        ParametrisedPolynomial differentiatedPoly = polynomial.differentiate()
                .add(polynomial.multiplyByScalar(exponential
                        .getExponentialCoefficient()));

        differentiatedComponent.setPolynomial(differentiatedPoly);
        differentiatedComponent.setExponential(exponential);

        return differentiatedComponent;
    }

    /**
     * Method adds two parametrised components.
     * @param component to be added
     * @return sum of two components
     */
    public final ParametrisedComponent add(final ParametrisedComponent component) {
        if (component.exponential.getExponentialCoefficient() != exponential.getExponentialCoefficient()
                || !Arrays.equals(component.polynomial.getParams(), polynomial.getParams())) {
            throw new IllegalArgumentException("Parametrised components don't match!");
        }

        ParametrisedComponent result = new ParametrisedComponent();
        result.setExponential(exponential);

        ParametrisedPolynomial polySum = component.polynomial.add(polynomial);
        result.setPolynomial(polySum);

        return result;
    }

    /**
     * Method multiplies the polynomial by a scalar value.
     * @param scalar to b multiplied by
     * @return result
     */
    public final ParametrisedComponent multiplyByScalar(final double scalar) {
        ParametrisedComponent result = new ParametrisedComponent();
        result.setExponential(exponential);

        ParametrisedPolynomial poly = polynomial.multiplyByScalar(scalar);
        result.setPolynomial(poly);

        return result;
    }

    /**
     * @return the polynomial
     */
    public final ParametrisedPolynomial getPolynomial() {
        return polynomial;
    }

    /**
     * @param poly the polynomial to set
     */
    public final void setPolynomial(final ParametrisedPolynomial poly) {
        polynomial = poly;
    }

    /**
     * @return the exponential
     */
    public final Exponential getExponential() {
        return exponential;
    }

    /**
     * @param exponent the exponential to set
     */
    public final void setExponential(final Exponential exponent) {
        exponential = exponent;
    }

}

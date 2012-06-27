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

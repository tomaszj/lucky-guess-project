/**
 *
 */
package pl.org.tomaszjaneczko.mouproject.math.ode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.org.tomaszjaneczko.mouproject.math.Polynomial;

/**
 * This class helps describing DEs of form a_(n)*y'{n}+a_(n-1)*y'{n-1} + ... +
 * a_1*y'+a_0*y = 0
 *
 * @author tomaszj
 *
 */
public class HomogenousDifferentialEquation {
    /** Coefficients of differential equation. */
    private Double[] coefficients;

    /**
     * Default constructor.
     *
     * @param diffCoeffs
     *            Array of consecutive coefficients of derivatives, with the
     *            highest order at the beginning.
     * @param nondiffCoeff
     *            Value of the coefficient including just the function y
     */
    public HomogenousDifferentialEquation(final Double[] diffCoeffs,
            final Double nondiffCoeff) {
        List<Double> coeffs = new ArrayList<Double>(Arrays.asList(diffCoeffs));
        coeffs.add(nondiffCoeff);
        Double[] fullCoeffs = coeffs.toArray(new Double[coeffs.size()]);

        coefficients = getReducedCoeffs(fullCoeffs);
    }

    /**
     * Method returns a characteristic equation in form of a polynomial.
     * @return characteristic polynomial
     */
    public final Polynomial getCharacteristicEquation() {
        return new Polynomial(coefficients);
    }

    /**
     * Method returns the simplest array for given coefficients. It's task is to
     * strip following zeros, which provide no particular information.
     *
     * @param coeffs
     *            Array of coefficients to be stripped
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
}

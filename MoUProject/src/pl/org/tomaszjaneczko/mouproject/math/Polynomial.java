package pl.org.tomaszjaneczko.mouproject.math;

import java.util.Arrays;

/**
 * Class representing polynomials for use with the MoU
 * @author tomaszj
 *
 */
public class Polynomial {

    private Double[] coefficients;

    /**
     * Public constructor which uses stripped coefficients.
     * @param coefficients of a polynomial
     */
    public Polynomial(final Double[] coefficients) {

        this.coefficients = getReducedCoeffs(coefficients);
    }

    /**
     * Method returns a coefficient at given index.
     * @param index of a coefficient
     * @return coefficient value
     */
    public Double getCoefficient(final int index) {
        return coefficients[index];
    }

    /**
     * Method return the degree of a polynomial.
     * @return degree of a polynomial
     */
    public int getDegree() {
        if (coefficients.length >= 1) {
            return coefficients.length - 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        PolynomialRenderer renderer = new PolynomialRenderer(this);
        return renderer.render();
    }

    @Override
    public boolean equals(final Object obj) {
        return Arrays.equals(coefficients, ((Polynomial) obj).coefficients);
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
            return new Double[] { 0.0 };
        }
    }

    private static class PolynomialRenderer {
        private Polynomial polynomial;

        public PolynomialRenderer(final Polynomial poly) {
            polynomial = poly;
        }

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

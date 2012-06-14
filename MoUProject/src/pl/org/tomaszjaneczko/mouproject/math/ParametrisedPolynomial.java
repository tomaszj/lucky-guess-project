/**
 */
package pl.org.tomaszjaneczko.mouproject.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a parametrised polynomial.
 * Form:
 * x^k * (A*a1 + B*a2 + ...) + x^(k-1) * (A*b1 + ...) +
 *
 * Examples:
 * Ax^2 + Bx + C
 * (A - B)x^2 + (C-A)x + B + A
 *
 * @author tomaszj
 *
 */
public class ParametrisedPolynomial {

    /** Array of parameters stored as strings. */
    private String[] params;

    /** Array representing linear combinations of parameters to reflect the polynomial. */
    private Double[][] paramValues;

    /**
     * Constructor, creating polynomial of form:
     * params[0] * x^k + params[1] * x^(k-1) + ... + params[k]
     * @param parameters - Array of params
     */
    public ParametrisedPolynomial(final String[] parameters) {
        params = parameters;

        int matrixSize = parameters.length;

        paramValues = createZeroMatrixOfSize(matrixSize);

        for (int i = 0; i < matrixSize; i++) {
            paramValues[i][i] = 1.0;
        }
    }

    /**
     * Protected constructor for creating a zero polynomial for given parameters.
     * @param size number of parameters and degree-1 at the same time
     */
    protected ParametrisedPolynomial(final int size) {
        params = new String[size];

        paramValues = createZeroMatrixOfSize(size);
    }

    /**
     * Method returns params used in the polynomial.
     * @return params used in polynomial
     */
    public final String[] getParams() {
        return params;
    }

    /**
     * Param values for a given degree of a polynomial.
     * @param degree of a polynomial component
     * @return map representing a linear combination of parameters
     */
    public final Map<String, Double> getParamValuesForDegree(final int degree) {

        Map<String, Double> paramsMap = new HashMap<String, Double>();
        int indexOfDegree = params.length - degree - 1;

        for (int i = 0; i < params.length; i++) {
            paramsMap.put(params[i], paramValues[indexOfDegree][i]);
        }

        return paramsMap;
    }

    /**
     * Param values for a given degree of a polynomial.
     * @param degree of a polynomial component
     * @return array representing a linear combination of parameters
     */
    public final Double[] getParamValuesForDegreeAsArray(final int degree) {

        Double[] result = new Double[params.length];

        int indexOfDegree = params.length - degree - 1;

        for (int i = 0; i < params.length; i++) {
            result[i] = paramValues[indexOfDegree][i];
        }

        return result;
    }

    /**
     * Method adds two parametrised polynomials, summing appropriate parameters.
     * @param poly to be added
     * @return sum of two polynomials
     */
    public final ParametrisedPolynomial add(final ParametrisedPolynomial poly) {
        ParametrisedPolynomial resultPoly = new ParametrisedPolynomial(params.length);

        if (!Arrays.equals(params, poly.params)) {
            // Throw an exception if arrays aren't equal
            throw new IllegalArgumentException("Param arrays should be the same!");
        }

        resultPoly.params = params;

        for (int i = 0; i < params.length; i++) {
            for (int j = 0; j < params.length; j++) {
                resultPoly.paramValues[i][j] = paramValues[i][j] + poly.paramValues[i][j];
            }
        }

        return resultPoly;
    }

    /**
     * Method multiplies a polynomial by a given scalar.
     * @param scalar used to multiply
     * @return multiplied polynomial
     */
    public final ParametrisedPolynomial multiplyByScalar(final double scalar) {
        ParametrisedPolynomial resultPoly = new ParametrisedPolynomial(params.length);

        resultPoly.params = params;

        for (int i = 0; i < params.length; i++) {
            for (int j = 0; j < params.length; j++) {
                resultPoly.paramValues[i][j] = paramValues[i][j] * scalar;
            }
        }

        return resultPoly;
    }

    /**
     * Method returns a matrix of all values.
     * @return matrix of all params for given degrees
     */
    public final Double[][] getParamMatrix() {
        return paramValues;
    }

    /**
     * Method differentiating a polynomial and returning its derived version.
     * @return differentiated polynomial
     */
    public final ParametrisedPolynomial differentiate() {
        ParametrisedPolynomial resultPoly = new ParametrisedPolynomial(params.length);
        resultPoly.params = params;

        for (int powerIndex = 0; powerIndex < params.length - 1; powerIndex++) {
            int powerValue = params.length - powerIndex - 1;

            for (int j = 0; j < params.length; j++) {
                resultPoly.paramValues[powerIndex + 1][j] = powerValue * paramValues[powerIndex][j];
            }
        }

        return resultPoly;
    }

    /**
     * Method creates a matrix with all elements set to zero.
     * @param size of the matrix
     * @return zero matrix
     */
    private Double[][] createZeroMatrixOfSize(final int size) {

        Double[][] zeroMatrix = new Double[size][size];

        // Zero the values of the matrix
        for (Double[] vector : zeroMatrix) {
            for (int i = 0; i < vector.length; i++) {
                vector[i] = 0.0;
            }
        }

        return zeroMatrix;
    }

}

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

        paramValues = createZeroMatrixForDegreeAndParamsCount(matrixSize - 1, matrixSize);

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

        paramValues = createZeroMatrixForDegreeAndParamsCount(size - 1, size);
    }

    /**
     * Constructor creating a parametrised polynomial of a given degree with
     * specific number of parameters.
     *
     * @param degree of a polynomial
     * @param paramsCount Count of parameters used. They still need to be set!
     */
    protected ParametrisedPolynomial(final int degree, final int paramsCount) {
        params = new String[paramsCount];

        paramValues = createZeroMatrixForDegreeAndParamsCount(degree, paramsCount);
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
        int indexOfDegree = getPolynomialDegree() - degree;

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

        int indexOfDegree = getPolynomialDegree() - degree;

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
        ParametrisedPolynomial resultPoly = new ParametrisedPolynomial(getPolynomialDegree(), params.length);

        if (!Arrays.equals(params, poly.params) || getPolynomialDegree() != poly.getPolynomialDegree()) {
            // Throw an exception if arrays aren't equal
            throw new IllegalArgumentException("Param arrays should be the same!");
        }

        resultPoly.params = params;

        for (int i = 0; i <= getPolynomialDegree(); i++) {
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
        ParametrisedPolynomial resultPoly = new ParametrisedPolynomial(
                getPolynomialDegree(), params.length);

        resultPoly.params = params;

        for (int i = 0; i <= getPolynomialDegree(); i++) {
            for (int j = 0; j < params.length; j++) {
                resultPoly.paramValues[i][j] = paramValues[i][j] * scalar;
            }
        }

        return resultPoly;
    }

    /**
     * Method multiplies the parametrised polynomial by a polynomial given.
     * @param polynomial to be used for multiplication
     * @return multiplied parametrised polynomial
     */
    public final ParametrisedPolynomial multiplyByPolynomial(final Polynomial polynomial) {
        int polynomialDegree = polynomial.getDegree();

        // Extend the polynomial to appropriate size.
        ParametrisedPolynomial resultPoly = new ParametrisedPolynomial(
                getPolynomialDegree() + polynomialDegree, params.length);
        resultPoly.params = params;

        for (int polynomialDegreeIndex = 0; polynomialDegreeIndex <= polynomialDegree; polynomialDegreeIndex++) {
            ParametrisedPolynomial scaledPoly = this.multiplyByScalar(polynomial
                    .getCoefficientForDegree(polynomialDegreeIndex));

            for (int i = 0; i <= getPolynomialDegree(); i++) {
                for (int j = 0; j < params.length; j++) {
                    resultPoly.paramValues[i + polynomialDegree
                                           - polynomialDegreeIndex][j] += scaledPoly.paramValues[i][j];
                }
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
        ParametrisedPolynomial resultPoly = new ParametrisedPolynomial(
                getPolynomialDegree(), params.length);
        resultPoly.params = params;

        for (int powerIndex = 0; powerIndex <= getPolynomialDegree() - 1; powerIndex++) {
            int powerValue = getPolynomialDegree() - powerIndex;

            for (int j = 0; j < params.length; j++) {
                resultPoly.paramValues[powerIndex + 1][j] = powerValue * paramValues[powerIndex][j];
            }
        }

        return resultPoly;
    }

    /**
     * Method evaluates Parametrised Polynomial using given values and returns respective polynomial.
     * @param values of consecutive parameters
     * @return evaluated polynomial
     */
    public final Polynomial getPolynomialForParameterValues(final Double[] values) {
        Double[] coefficients = new Double[paramValues.length];

        if (params.length != values.length) {
            throw new IllegalArgumentException("Parameters and values arrays don't match!");
        }

        for (int i = 0; i < paramValues.length; i++) {

            double sum = 0;
            for (int j = 0; j < params.length; j++) {
                sum += paramValues[i][j] * values[j];
            }

            coefficients[i] = sum;
        }

        return new Polynomial(coefficients);
    }

    /**
     * Factory method used to create a zero polynomial using given parameters.
     * @param params Parameters used in a polynomial
     * @return zero parametrised polynomial
     */
    public static ParametrisedPolynomial zeroPolynomial(final String[] params) {
        ParametrisedPolynomial poly = new ParametrisedPolynomial(params.length);
        poly.params = params;

        return poly;
    }

    /**
     * Method creates an array of default params for given count. It's in the form of "a1", "a2", "a3", etc.
     * @param count of params requested
     * @return list of params
     */
    public static String[] getDefaultParamsOfCount(final int count) {
        String[] params = new String[count];
        for (int i = 0; i < count; i++) {
            params[i] = "a" + String.valueOf(i + 1);
        }

        return params;
    }

    /**
     * Method creates a matrix with all elements set to zero.
     * @param degree of a polynomial
     * @param paramsCount count of params used
     * @return zero matrix
     */
    private Double[][] createZeroMatrixForDegreeAndParamsCount(final int degree, final int paramsCount) {

        Double[][] zeroMatrix = new Double[degree + 1][paramsCount];

        // Zero the values of the matrix
        for (Double[] row : zeroMatrix) {
            Arrays.fill(row, 0.0);
        }

        return zeroMatrix;
    }

    /**
     * Method returns a polynomial degree.
     * @return polynomial degree
     */
    private int getPolynomialDegree() {
        if (paramValues.length == 0) {
            return 0;
        } else {
            return paramValues.length - 1; // Number of rows determines the degree of a polynomial
        }
    }

}

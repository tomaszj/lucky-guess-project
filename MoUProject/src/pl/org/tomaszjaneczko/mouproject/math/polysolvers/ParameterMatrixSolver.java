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

import Jama.Matrix;

/**
 * Solver class for parameter matrix.
 * @author tomaszj
 */
public class ParameterMatrixSolver {

    /**
     * Parameter matrix to be solved.
     */
    private Double[][] paramMatrix;

    /**
     * Values vector.
     */
    private Double[] valuesVector;

    /**
     * Default constructor.
     * @param parametersMatrix describing the equations for parameters
     * @param paramValuesVector parameter values
     */
    public ParameterMatrixSolver(final Double[][] parametersMatrix, final Double[] paramValuesVector) {
        paramMatrix = parametersMatrix;

        valuesVector = paramValuesVector;
    }

    /**
     * Method solves the given equation system for polynomial parameters.
     * @return parameter values
     * @throws WrongParameterMatrixException thrown when parameter matrix is badly formed
     */
    public final Double[] solve() throws WrongParameterMatrixException {
        // We've got the poly ready for calculation
        double[][] tableA = new double[paramMatrix.length][paramMatrix[0].length];
        for (int i = 0; i < tableA.length; i++) {
            for (int j = 0; j < tableA[0].length; j++) {
                tableA[i][j] = paramMatrix[i][j];
            }
        }

        // Get the matrix B
        double[] vectorB = new double[valuesVector.length];
        for (int i = 0; i < vectorB.length; i++) {
            vectorB[i] = valuesVector[i];
        }

        Matrix matrixA = new Matrix(tableA);
        Matrix matrixB = new Matrix(vectorB, 1).transpose();


        Matrix nonSingularMatrix = null;
        for (int i = 0; i <= matrixA.getRowDimension() - matrixA.getColumnDimension(); i++) {
            Matrix subMatrixA = matrixA.getMatrix(i,
                    i + matrixA.getColumnDimension() - 1, 0,
                    matrixA.getColumnDimension() - 1);

            if (subMatrixA.rank() == subMatrixA.getRowDimension()) {
                nonSingularMatrix = subMatrixA;
                break;
            }
        }

        if (nonSingularMatrix == null) {
            throw new WrongParameterMatrixException();
        } else {
            double[] solution = nonSingularMatrix.solve(matrixB).getRowPackedCopy();
            Double[] solutionAsObjects = new Double[solution.length];

            for (int i = 0; i < solution.length; i++) {
                solutionAsObjects[i] = solution[i];
            }

            return solutionAsObjects;
        }

    }
}

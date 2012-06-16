/**
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

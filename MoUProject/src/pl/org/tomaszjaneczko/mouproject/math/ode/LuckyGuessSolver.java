/**
 * 
 */
package pl.org.tomaszjaneczko.mouproject.math.ode;

import pl.org.tomaszjaneczko.mouproject.math.ComplexNumber;
import pl.org.tomaszjaneczko.mouproject.math.Exponential;
import pl.org.tomaszjaneczko.mouproject.math.ParametrisedPolynomial;
import pl.org.tomaszjaneczko.mouproject.math.Polynomial;
import pl.org.tomaszjaneczko.mouproject.math.SineAndCosine;
import pl.org.tomaszjaneczko.mouproject.math.polysolvers.IPolynomialSolver;
import pl.org.tomaszjaneczko.mouproject.math.polysolvers.ParameterMatrixSolver;
import pl.org.tomaszjaneczko.mouproject.math.polysolvers.PolynomialSolverFactory;
import pl.org.tomaszjaneczko.mouproject.math.polysolvers.WrongParameterMatrixException;

/**
 * Class which solves linear differential equations with constant coefficients.
 * @author tomaszj
 */
public class LuckyGuessSolver {

    private HomogenousDifferentialEquation diffEqn;
    private String independentEqn;

    private GeneralSolution generalSolution;
    private String particularSolution;

    public LuckyGuessSolver(final Double[] diffCoefficients, final Double nonDiffCoefficient, final String indieEqn) {
        diffEqn = new HomogenousDifferentialEquation(diffCoefficients, nonDiffCoefficient);
        independentEqn = indieEqn;
    }

    public void solve() {
        // Follow the standard procedure - get the characteristic equation and find its roots.
        Polynomial characteristicEqn = diffEqn.getCharacteristicEquation();
        IPolynomialSolver solver = PolynomialSolverFactory.getSolverForPolynomial(characteristicEqn);
        ComplexNumber[] roots = solver.findRoots();

        // Find the solution basis.
        SolutionBasis basis = new SolutionBasis(roots);

        // And find the general solution.
        generalSolution = new GeneralSolution(basis);

        // Get the solution component for the independent equation
        Polynomial mainPolynomial = new Polynomial(new Double[] { 1.0, 0.0, 0.0});

        SolutionComponent independentComponent = new SolutionComponent(
                mainPolynomial,
                Exponential.getSingularExponential(),
                SineAndCosine.getSingularSineAndCosine());

        // Check if the independent coefficient is a root of characteristic equation
        int rootMultiplier = 0;
        for (ComplexNumber root : roots) {
            if (root.getRealPart() == independentComponent.getExponential()
                    .getExponentialCoefficient()
                    && root.getImaginaryPart() == independentComponent
                    .getSineAndCosine().getTrigCoefficient()) {
                rootMultiplier++;
            }
        }

        // Based on the multiplier of the root, find an appropriate (single argument) polynomial, to multiply the independent eqn.
        Polynomial multiplierPolynomial = Polynomial.getSingleArgumentPolynomialOfDegree(rootMultiplier);

        // Create parameter names
        String[] params = ParametrisedPolynomial.getDefaultParamsOfCount(mainPolynomial.getDegree() + 1);

        //TODO: Full component should be placed here
        ParametrisedPolynomial paramPoly = new ParametrisedPolynomial(params);
        paramPoly = paramPoly.multiplyByPolynomial(multiplierPolynomial);

        ParametrisedPolynomial totalPoly = paramPoly.multiplyByScalar(diffEqn.getCoefficientForDegree(0));
        ParametrisedPolynomial differentiatedPoly = paramPoly;

        int differentialEquationDegree = diffEqn.getDegree();

        for (int i = 1; i <= differentialEquationDegree; i++) {
            differentiatedPoly = differentiatedPoly.differentiate();
            totalPoly = totalPoly.add(differentiatedPoly.multiplyByScalar(diffEqn.getCoefficientForDegree(i)));
        }

        Double[][] parametrisedPolyMatrix = totalPoly.getParamMatrix();
        Double[] mainPolynomialCoefficients = new Double[mainPolynomial.getDegree() + 1];
        for (int i = 0; i <= mainPolynomial.getDegree(); i++) {
            mainPolynomialCoefficients[i] = mainPolynomial.getCoefficient(i);
        }

        ParameterMatrixSolver matrixSolver = new ParameterMatrixSolver(
                parametrisedPolyMatrix, mainPolynomialCoefficients);
        try {
            Double[] paramsSolution = matrixSolver.solve();

            Polynomial polynomial = paramPoly.getPolynomialForParameterValues(paramsSolution);

            particularSolution = polynomial.toString();
        } catch (WrongParameterMatrixException e) {
            // TODO: handle exception
        }
    }

    /**
     * @return the generalSolution
     */
    public final GeneralSolution getGeneralSolution() {
        return generalSolution;
    }

    public String getParticularSolution() {
        return particularSolution;
    }

    @Override
    public final String toString() {
        if (generalSolution == null) {
            return "Not yet solved!";
        } else {
            return generalSolution.toString() + " + " + particularSolution;
        }
    }


}

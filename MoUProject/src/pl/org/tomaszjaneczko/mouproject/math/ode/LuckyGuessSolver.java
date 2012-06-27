/**
 */
package pl.org.tomaszjaneczko.mouproject.math.ode;

import pl.org.tomaszjaneczko.mouproject.math.ComplexNumber;
import pl.org.tomaszjaneczko.mouproject.math.Exponential;
import pl.org.tomaszjaneczko.mouproject.math.ParametrisedComponent;
import pl.org.tomaszjaneczko.mouproject.math.ParametrisedPolynomial;
import pl.org.tomaszjaneczko.mouproject.math.Polynomial;
import pl.org.tomaszjaneczko.mouproject.math.SineAndCosine;
import pl.org.tomaszjaneczko.mouproject.math.ode.CalculationFailedException.CalculationFailedReason;
import pl.org.tomaszjaneczko.mouproject.math.polysolvers.IPolynomialSolver;
import pl.org.tomaszjaneczko.mouproject.math.polysolvers.ParameterMatrixSolver;
import pl.org.tomaszjaneczko.mouproject.math.polysolvers.PolynomialSolverFactory;
import pl.org.tomaszjaneczko.mouproject.math.polysolvers.WrongParameterMatrixException;

/**
 * Class which solves linear differential equations with constant coefficients.
 * @author tomaszj
 */
public class LuckyGuessSolver {

    /** Homogenous differential equation. */
    private HomogenousDifferentialEquation diffEqn;

    /** Independent equation (of x variable). */
    private String independentEqn;

    /** General solution of the DE. */
    private GeneralSolution generalSolution;

    /** Particular solution of the DE. */
    private ParticularSolution particularSolution;

    /**
     * Default constructor for LuckyGuessSolver, used to solve linear
     * differential equation with constant coefficients using method of
     * variation of parameters.
     *
     * @param diffCoefficients
     *            Coefficients for derivatives of y
     * @param nonDiffCoefficient
     *            Coefficient for y
     * @param indieEqn
     *            Independent equation
     */
    public LuckyGuessSolver(final Double[] diffCoefficients, final Double nonDiffCoefficient, final String indieEqn) {
        diffEqn = new HomogenousDifferentialEquation(diffCoefficients, nonDiffCoefficient);
        independentEqn = indieEqn;
    }

    /**
     * Solves the linear differential equation.
     * @throws CalculationFailedException thrown when calculation fails
     */
    public final void solve() throws CalculationFailedException {
        // Follow the standard procedure - get the characteristic equation and find its roots.
        Polynomial characteristicEqn = diffEqn.getCharacteristicEquation();
        IPolynomialSolver solver = PolynomialSolverFactory.getSolverForPolynomial(characteristicEqn);
        ComplexNumber[] roots = solver.findRoots();

        // Find the solution basis.
        SolutionBasis basis = new SolutionBasis(roots);

        // And find the general solution.
        generalSolution = new GeneralSolution(basis);

        // Get the solution component for the independent equation
        Polynomial mainPolynomial = new Polynomial(new Double[] {1.0, 0.0, 0.0});

        EquationComponent independentComponent = new EquationComponent(
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

        // Based on the multiplier of the root, find an appropriate (single
        // argument) polynomial, to multiply the independent eqn.
        Polynomial multiplierPolynomial = Polynomial.getSingleArgumentPolynomialOfDegree(rootMultiplier);

        // Create parameter names.
        String[] params = ParametrisedPolynomial.getDefaultParamsOfCount(mainPolynomial.getDegree() + 1);

        // Multiply the independent equation.
        ParametrisedPolynomial paramPoly = new ParametrisedPolynomial(params);
        paramPoly = paramPoly.multiplyByPolynomial(multiplierPolynomial);

        ParametrisedComponent totalComponent = new ParametrisedComponent();
        totalComponent.setExponential(independentComponent.getExponential());
        totalComponent.setPolynomial(paramPoly.multiplyByScalar(diffEqn.getCoefficientForDegree(0)));

        ParametrisedComponent differentiatedComponent = new ParametrisedComponent();
        differentiatedComponent.setExponential(independentComponent.getExponential());
        differentiatedComponent.setPolynomial(paramPoly);

        // Perform the differentiation.
        int differentialEquationDegree = diffEqn.getDegree();

        // For each derivative degree, calculate the parametrised polynomial and substitute to differential equation.
        for (int i = 1; i <= differentialEquationDegree; i++) {
            differentiatedComponent = differentiatedComponent.differentiate();
            totalComponent = totalComponent.add(differentiatedComponent
                    .multiplyByScalar(diffEqn.getCoefficientForDegree(i)));
        }

        // Prepare the matrix equation of form A*x = b, where A is parameter
        // matrix and b is a vector of polynomial coefficients.
        Double[][] parametrisedPolyMatrix = totalComponent.getPolynomial().getParamMatrix();
        Double[] mainPolynomialCoefficients = new Double[mainPolynomial.getDegree() + 1];
        for (int i = 0; i <= mainPolynomial.getDegree(); i++) {
            mainPolynomialCoefficients[i] = mainPolynomial.getCoefficient(i);
        }

        // Create the solver.
        ParameterMatrixSolver matrixSolver = new ParameterMatrixSolver(
                parametrisedPolyMatrix, mainPolynomialCoefficients);

        // Do the calculation.
        try {
            Double[] paramsSolution = matrixSolver.solve();

            Polynomial polynomial = paramPoly.getPolynomialForParameterValues(paramsSolution);

            particularSolution = new ParticularSolution(new SolutionComponent(
                    polynomial, totalComponent.getExponential(),
                    SineAndCosine.getSingularSineAndCosine()));

        } catch (WrongParameterMatrixException e) {
            throw new CalculationFailedException(CalculationFailedReason.NO_SOLVABLE_PARAMETER_MATRIX_FOUND);
        }
    }

    /**
     * @return general solution
     */
    public final GeneralSolution getGeneralSolution() {
        return generalSolution;
    }

    /**
     * @return particular solution
     */
    public final ParticularSolution getParticularSolution() {
        return particularSolution;
    }

    @Override
    public final String toString() {
        if (generalSolution == null) {
            return "Not yet solved!";
        } else {
            return generalSolution.toString() + " + " + particularSolution.toString();
        }
    }


}

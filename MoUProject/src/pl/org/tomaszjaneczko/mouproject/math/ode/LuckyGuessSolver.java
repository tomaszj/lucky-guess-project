/**
 * 
 */
package pl.org.tomaszjaneczko.mouproject.math.ode;

import pl.org.tomaszjaneczko.mouproject.math.ComplexNumber;
import pl.org.tomaszjaneczko.mouproject.math.Polynomial;
import pl.org.tomaszjaneczko.mouproject.math.polysolvers.IPolynomialSolver;
import pl.org.tomaszjaneczko.mouproject.math.polysolvers.PolynomialSolverFactory;

/**
 * Class which solves linear differential equations with constant coefficients.
 * @author tomaszj
 */
public class LuckyGuessSolver {

    private HomogenousDifferentialEquation diffEqn;
    private String independentEqn;

    private GeneralSolution generalSolution;

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
    }

    /**
     * @return the generalSolution
     */
    public final GeneralSolution getGeneralSolution() {
        return generalSolution;
    }

    @Override
    public String toString() {
        if (generalSolution == null) {
            return "Not yet solved!";
        } else {
            return generalSolution.toString();
        }
    }


}

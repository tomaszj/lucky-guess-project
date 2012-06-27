/**
 */
package pl.org.tomaszjaneczko.mouproject.math.ode;

import pl.org.tomaszjaneczko.mouproject.math.Polynomial;


/**
 * Class describing a particular solution to the differential equation.
 * @author tomaszj
 *
 */
public class ParticularSolution {

    /** Solution component, making up the particular solution. */
    private final SolutionComponent solutionComponent;

    /**
     * Default constructor accepting a single solution component.
     * @param solutionComp to be set
     */
    public ParticularSolution(final SolutionComponent solutionComp) {
        solutionComponent = solutionComp;
    }

    /**
     * Method determines if solution is empty after solving.
     * @return true if the solution is 0.0
     */
    public final boolean isZeroSolution() {
        return solutionComponent.getPolynomial().equals(Polynomial.getZeroPolynomial());
    }

    @Override
    public final String toString() {
        return solutionComponent.toString();
    }
}

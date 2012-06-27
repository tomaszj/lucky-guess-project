/**
 */
package pl.org.tomaszjaneczko.mouproject.math.ode;


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

    @Override
    public final String toString() {
        return solutionComponent.toString();
    }
}

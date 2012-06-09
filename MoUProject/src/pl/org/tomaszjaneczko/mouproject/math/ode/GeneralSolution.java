/**
 *
 */
package pl.org.tomaszjaneczko.mouproject.math.ode;

/**
 * @author tomaszj
 *
 */
public class GeneralSolution {

    /** Solution basis. */
    private SolutionBasis solutionBasis;

    /**
     * Default constructor.
     * @param basis used to determine the General Solution
     */
    public GeneralSolution(final SolutionBasis basis) {
        solutionBasis = basis;
    }

    @Override
    public final String toString() {
        return super.toString();
    }
}

/**
 *
 */
package pl.org.tomaszjaneczko.mouproject.math.ode;

/**
 * Class describing a general soltion to the differential equation.
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
        int index = 0;
        StringBuilder result = new StringBuilder();
        for (EquationComponent component : solutionBasis.getSolutionComponents()) {

            if (index > 0) {
                // Consecutive element
                result.append("+");
            }

            result.append("C");
            result.append(index);
            result.append("*");

            result.append(component.toString());

            index++;
        }

        return result.toString();
    }

}

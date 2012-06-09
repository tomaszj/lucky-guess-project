/**
 * 
 */
package pl.org.tomaszjaneczko.mouproject.math.ode;

import java.util.ArrayList;
import java.util.List;

import pl.org.tomaszjaneczko.mouproject.math.Polynomial;

/**
 * @author tomaszj
 *
 */
public class SolutionBasis {

    /** Solution components in the solution basis. */
    private final List<SolutionComponent> solutionComponents;

    /**
     * Default constructor.
     * @param characteristicEquation used to determine the solution components.
     */
    public SolutionBasis(final Polynomial characteristicEquation) {

        solutionComponents = new ArrayList<SolutionComponent>();
    }
}

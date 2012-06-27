/**
 */
package pl.org.tomaszjaneczko.mouproject.math.ode;

import pl.org.tomaszjaneczko.mouproject.math.Exponential;
import pl.org.tomaszjaneczko.mouproject.math.Polynomial;
import pl.org.tomaszjaneczko.mouproject.math.SineAndCosine;

/**
 * Class representing a solution component, to mark the difference from the ordinary equation component.
 * @author tomaszj
 */
public class SolutionComponent extends EquationComponent {

    /**
     * Default constructor using polynomial, exponential and sine and cosine component.
     * @param poly Polynomial to be set
     * @param exponent Exponential to be set
     * @param sinAndCos Sine and Cosine component to be set
     */
    public SolutionComponent(final Polynomial poly, final Exponential exponent,
            final SineAndCosine sinAndCos) {
        super(poly, exponent, sinAndCos);
    }

}

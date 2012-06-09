/**
 * 
 */
package pl.org.tomaszjaneczko.mouproject.math.ode;

import pl.org.tomaszjaneczko.mouproject.math.Exponential;
import pl.org.tomaszjaneczko.mouproject.math.Polynomial;
import pl.org.tomaszjaneczko.mouproject.math.SineAndCosine;

/**
 * Class representing a single solution component.
 * @author tomaszj
 */
public class SolutionComponent {
    /** Polynomial component. */
    private Polynomial polynomial;

    /** Exponential component. */
    private Exponential exponential;

    /** Trig component. */
    private SineAndCosine sineAndCosine;

    /**
     * Default constructor.
     * @param poly
     *            component
     * @param exponent
     *            component
     * @param sinAndCos
     *            component
     */
    public SolutionComponent(final Polynomial poly, final Exponential exponent,
            final SineAndCosine sinAndCos) {
        polynomial = poly;
        exponential = exponent;
        sineAndCosine = sinAndCos;
    }

    @Override
    public final boolean equals(final Object obj) {
        SolutionComponent other = (SolutionComponent) obj;
        return polynomial.equals(other.polynomial)
                && exponential.equals(other.exponential)
                && sineAndCosine.equals(other.sineAndCosine);
    }

}

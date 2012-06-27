/**
 */
package pl.org.tomaszjaneczko.mouproject.math.ode;

import pl.org.tomaszjaneczko.mouproject.math.Exponential;
import pl.org.tomaszjaneczko.mouproject.math.Polynomial;
import pl.org.tomaszjaneczko.mouproject.math.SineAndCosine;

/**
 * Class representing a single solution component.
 * @author tomaszj
 */
public class EquationComponent {
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
    public EquationComponent(final Polynomial poly, final Exponential exponent,
            final SineAndCosine sinAndCos) {
        polynomial = poly;
        exponential = exponent;
        sineAndCosine = sinAndCos;
    }

    /**
     * @return the polynomial
     */
    public final Polynomial getPolynomial() {
        return polynomial;
    }

    /**
     * @return the exponential
     */
    public final Exponential getExponential() {
        return exponential;
    }

    /**
     * @return the sineAndCosine
     */
    public final SineAndCosine getSineAndCosine() {
        return sineAndCosine;
    }

    @Override
    public final int hashCode() {
        return 0;
    }

    @Override
    public final boolean equals(final Object obj) {
        EquationComponent other = (EquationComponent) obj;
        return polynomial.equals(other.polynomial)
                && exponential.equals(other.exponential)
                && sineAndCosine.equals(other.sineAndCosine);
    }

    @Override
    public final String toString() {
        SolutionStringRenderer renderer = new SolutionStringRenderer(this);
        return renderer.render();
    }

    /**
     * Class used to render string out of SolutionComponent.
     * @author tomaszj
     */
    private class SolutionStringRenderer {

        /** Component to be rendered. */
        private EquationComponent component;

        /**
         * Default constructor.
         * @param solutionComp component to be rendered
         */
        public SolutionStringRenderer(final EquationComponent solutionComp) {
            component = solutionComp;
        }

        /**
         * Method used to render the String.
         * @return Rendered string.
         */
        public String render() {
            StringBuilder result = new StringBuilder();

            result.append("(");
            result.append(component.getPolynomial());
            result.append(")");

            if (!Exponential.getSingularExponential().equals(component.getExponential())) {
                // If the exponential is different from singular exponential
                result.append("*");

                result.append(component.getExponential());
            }

            if (!SineAndCosine.getSingularSineAndCosine().equals(component.getSineAndCosine())) {
                result.append("*");
                result.append("(");
                result.append(component.getSineAndCosine());
                result.append(")");
            }

            return result.toString();

        }
    }

}

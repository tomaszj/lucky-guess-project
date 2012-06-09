/**
 *
 */
package pl.org.tomaszjaneczko.mouproject.math;

/**
 * Class representing the trig component of a solution.
 * @author tomaszj
 */
public class SineAndCosine {

    /** Polynomial multiplied by the sine. */
    private final Polynomial sinePolynomial;

    /** Polynomial multiplied by the cosine. */
    private final Polynomial cosinePolynomial;

    /** Coefficient of sine and cosine (sin Bx + cos Bx). */
    private final double trigCoefficient;

    /**
     * Constructor.
     * @param beta
     *            trig coefficient
     * @param sinePoly
     *            polynomial by the sine component
     * @param cosinePoly
     *            polynomial by the cosine component
     */
    public SineAndCosine(final double beta, final Polynomial sinePoly,
            final Polynomial cosinePoly) {
        trigCoefficient = beta;
        sinePolynomial = sinePoly;
        cosinePolynomial = cosinePoly;
    }

    @Override
    public final String toString() {
        SineAndCosineRenderer renderer = new SineAndCosineRenderer(this);
        return renderer.render();
    }

    /**
     * @return the sinePolynomial
     */
    public final Polynomial getSinePolynomial() {
        return sinePolynomial;
    }

    /**
     * @return the cosinePolynomial
     */
    public final Polynomial getCosinePolynomial() {
        return cosinePolynomial;
    }

    /**
     * @return the trigCoefficient
     */
    public final double getTrigCoefficient() {
        return trigCoefficient;
    }

    /**
     * Class which is responsible for rendering string from SineAndCosine.
     * @author tomaszj
     */
    private class SineAndCosineRenderer {

        /** Sine and cosine to be rendered. */
        private SineAndCosine sineAndCosine;

        /**
         * Default constructor.
         * @param sinAndCos SineAndCosine object to be rendered.
         */
        public SineAndCosineRenderer(final SineAndCosine sinAndCos) {
            sineAndCosine = sinAndCos;
        }

        /**
         * Method renders the SineAndCosine to a human readable string.
         * @return Rendered SineAndCosine
         */
        public String render() {
            double beta = sineAndCosine.getTrigCoefficient();

            String sinePoly = sineAndCosine.getSinePolynomial().toString();
            String cosinePoly = sineAndCosine.getCosinePolynomial().toString();

            StringBuilder trigComponent = new StringBuilder();

            // Append the sine component
            if (sinePoly.length() > 0 && !"0.0".equals(sinePoly)) {
                trigComponent.append("(");
                trigComponent.append(sinePoly);
                trigComponent.append(")*sin(");
                trigComponent.append(beta);
                trigComponent.append("*x)");

                // Append plus sign if cosinePoly is non-zero
                if (cosinePoly.length() > 0 && !"0.0".equals(cosinePoly)) {
                    trigComponent.append("+");
                }
            }

            // Append the cosine component
            if (cosinePoly.length() > 0 && !"0.0".equals(cosinePoly)) {
                trigComponent.append("(");
                trigComponent.append(cosinePoly);
                trigComponent.append(")*cos(");
                trigComponent.append(beta);
                trigComponent.append("*x)");
            }

            return trigComponent.toString();
        }
    }

}

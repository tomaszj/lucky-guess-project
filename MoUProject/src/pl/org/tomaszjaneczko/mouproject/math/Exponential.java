/**
 */
package pl.org.tomaszjaneczko.mouproject.math;

/**
 * Class representing exponentials.
 * @author tomaszj
 */
public class Exponential {

    /** Coefficient for the exponent function. */
    private double exponentialCoefficient;

    /**
     * Default constructor for exponential of form e^(alpha * x).
     * @param alpha the exponential coefficient
     */
    public Exponential(final double alpha) {
        exponentialCoefficient = alpha;
    }

    /**
     * Method return a singular exponential.
     * @return singular exponential
     */
    public static Exponential getSingularExponential() {
        return new Exponential(0.0);
    }

    /**
     * Getter for exponential coefficient.
     * @return exponential coefficient
     */
    public final double getExponentialCoefficient() {
        return exponentialCoefficient;
    }

    @Override
    public final boolean equals(final Object obj) {
        Exponential other = (Exponential) obj;
        return exponentialCoefficient == other.exponentialCoefficient;
    }

    @Override
    public final String toString() {
        if (exponentialCoefficient == 0) {
            return "1";
        } else {
            return "e^(" + String.valueOf(exponentialCoefficient) + "*x)";
        }
    }

}

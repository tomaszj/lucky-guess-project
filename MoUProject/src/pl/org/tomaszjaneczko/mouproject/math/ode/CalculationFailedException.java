/**
 */
package pl.org.tomaszjaneczko.mouproject.math.ode;

/**
 * Exception thrown when calculations in the program failed.
 * @author tomaszj
 */
public class CalculationFailedException extends Exception {

    /**
     * Serializing id.
     */
    private static final long serialVersionUID = -5464771228339200884L;

    /** Reason of failure. */
    private final CalculationFailedReason reason;

    /**
     * Constructor accepting a reason to be specified.
     * @param failingReason of the calculation
     */
    public CalculationFailedException(final CalculationFailedReason failingReason) {
        reason = failingReason;
    }

    /**
     * Getter for the failing reason.
     * @return the reason
     */
    public final CalculationFailedReason getReason() {
        return reason;
    }

    /** Enumeration used to determine gently the reason of failing. */
    public static enum CalculationFailedReason {
        /** Reason raised when no proper parameter matrix was found. */
        NO_SOLVABLE_PARAMETER_MATRIX_FOUND
    }

}

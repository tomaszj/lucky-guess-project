/**
 * Method of Undetermined Coefficients Project
 *
 * Created by Tomasz Janeczko 2012
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
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

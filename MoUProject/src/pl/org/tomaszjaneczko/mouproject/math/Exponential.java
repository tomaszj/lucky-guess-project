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
 *
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
    public final int hashCode() {
        return 0;
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

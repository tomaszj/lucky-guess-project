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

import pl.org.tomaszjaneczko.mouproject.math.Polynomial;


/**
 * Class describing a particular solution to the differential equation.
 * @author tomaszj
 *
 */
public class ParticularSolution {

    /** Solution component, making up the particular solution. */
    private final SolutionComponent solutionComponent;

    /**
     * Default constructor accepting a single solution component.
     * @param solutionComp to be set
     */
    public ParticularSolution(final SolutionComponent solutionComp) {
        solutionComponent = solutionComp;
    }

    /**
     * Method determines if solution is empty after solving.
     * @return true if the solution is 0.0
     */
    public final boolean isZeroSolution() {
        return solutionComponent.getPolynomial().equals(Polynomial.getZeroPolynomial());
    }

    @Override
    public final String toString() {
        return solutionComponent.toString();
    }
}

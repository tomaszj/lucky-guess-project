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
 * Class describing a general soltion to the differential equation.
 * @author tomaszj
 *
 */
public class GeneralSolution {

    /** Solution basis. */
    private SolutionBasis solutionBasis;

    /**
     * Default constructor.
     * @param basis used to determine the General Solution
     */
    public GeneralSolution(final SolutionBasis basis) {
        solutionBasis = basis;
    }

    @Override
    public final String toString() {
        int index = 0;
        StringBuilder result = new StringBuilder();
        for (EquationComponent component : solutionBasis.getSolutionComponents()) {

            if (index > 0) {
                // Consecutive element
                result.append("+");
            }

            result.append("C");
            result.append(index);
            result.append("*");

            result.append(component.toString());

            index++;
        }

        return result.toString();
    }

}

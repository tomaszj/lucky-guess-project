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

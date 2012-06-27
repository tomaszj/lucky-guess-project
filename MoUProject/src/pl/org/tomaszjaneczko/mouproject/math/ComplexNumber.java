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
 * Class representing complex numbers.
 * @author tomaszj
 *
 */
public class ComplexNumber {

    /** The real part of a complex number. */
    private double realPart;

    /** The imaginary part of a complex number. */
    private double imaginaryPart;

    /**
     * Default constructor accepting arguments in rectangular format.
     * @param real Real value
     * @param imaginary Imaginary value
     */
    public ComplexNumber(final double real, final double imaginary) {
        realPart = real;
        imaginaryPart = imaginary;
    }

    /**
     * @return the realPart
     */
    public final double getRealPart() {
        return realPart;
    }

    /**
     * @return the imaginaryPart
     */
    public final double getImaginaryPart() {
        return imaginaryPart;
    }

    @Override
    public final int hashCode() {
        return 0;
    }

    @Override
    public final boolean equals(final Object obj) {
        ComplexNumber other = (ComplexNumber) obj;
        return realPart == other.realPart && imaginaryPart == other.imaginaryPart;
    }

    @Override
    public final String toString() {
        String realPartString = String.valueOf(realPart);
        String imagPartString = String.valueOf(imaginaryPart) + "i";

        StringBuilder complexAsString = new StringBuilder();

        complexAsString.append(realPartString);

        if (imaginaryPart >= 0.0) {
            complexAsString.append("+");
        }

        complexAsString.append(imagPartString);

        return complexAsString.toString();

    }



}

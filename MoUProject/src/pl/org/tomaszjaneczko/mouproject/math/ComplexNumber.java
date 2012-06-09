/**
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
    public boolean equals(final Object obj) {
        ComplexNumber other = (ComplexNumber)obj;
        return realPart == other.realPart && imaginaryPart == other.imaginaryPart;
    }

    @Override
    public final String toString() {
        String realPartString = String.valueOf(realPart);
        String imagPartString = String.valueOf(imaginaryPart) + "i";

        StringBuilder complexAsString = new StringBuilder();
        if (realPart != 0.0) {
            complexAsString.append(realPartString);

            if (imaginaryPart > 0.0) {
                complexAsString.append("+");
            }
        }

        if (imaginaryPart != 0.0) {
            complexAsString.append(imagPartString);
        }

        return complexAsString.toString();

    }



}

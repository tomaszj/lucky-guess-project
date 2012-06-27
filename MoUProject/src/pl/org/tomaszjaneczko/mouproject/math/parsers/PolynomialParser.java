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
package pl.org.tomaszjaneczko.mouproject.math.parsers;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.org.tomaszjaneczko.mouproject.math.Polynomial;

/**
 * Class representing a parser for polynomials in form of strings.
 * Form assumed: -?A.B*x^n{+|-}C.D*x^k{+|-}E.F*x{+|-}G.H
 * @author tomaszj
 */
public class PolynomialParser {

    /** Component regex. */
    private static final String COMPONENT_REGEX = "^([+\\- ])?(\\d+(?:\\.\\d*)?)?\\*?(x(?:\\^(\\d+))?)?$";

    /**
     * Parses a string and returns a Polynomial.
     * @param polynomialString to be parsed
     * @throws ParseException when the expression can't be parsed
     * @return polynomial
     */
    public final Polynomial parseString(final String polynomialString) throws ParseException {

        // Get the sorted set of the components
        SortedSet<PolyComponent> sortedComponents = extractPolyComponentsFromString(polynomialString
                .replaceAll("\\s", ""));

        // Find the highest degree in the set and initialise the array
        int highestDegree = sortedComponents.first().getPowerComponent();
        Double[] polyCoefficients = new Double[highestDegree + 1];
        Arrays.fill(polyCoefficients, 0.0);

        // Prepare the array of coefficients
        for (PolyComponent component : sortedComponents) {
            int degree = component.getPowerComponent();
            int indexOfDegree = polyCoefficients.length - degree - 1;
            polyCoefficients[indexOfDegree] = component.getNumberComponent();
        }

        // Create and return the polynomial
        return new Polynomial(polyCoefficients);

    }

    /**
     * Helper method which extracts polynomial components from a String.
     * @param polynomialString to be parsed
     * @throws ParseException when the expression can't be parsed
     * @return polynomial components
     */
    private SortedSet<PolyComponent> extractPolyComponentsFromString(final String polynomialString)
            throws ParseException {

        // Split the string into separate components
        String[] polyComponents = polynomialString.split("(?=[+\\-])");

        if (polyComponents.length > 0 && polyComponents[0] == null || polyComponents[0].isEmpty()) {
            polyComponents = Arrays.copyOfRange(polyComponents, 1, polyComponents.length);
        }


        // Compile the pattern used to handle compnonents
        Pattern polynomialComponentPattern = Pattern.compile(COMPONENT_REGEX);

        // Prepare a sorted set to insert newly parsed elements
        SortedSet<PolyComponent> components = new TreeSet<PolyComponent>(new Comparator<PolyComponent>() {
            @Override
            public int compare(final PolyComponent o1, final PolyComponent o2) {
                return o2.getPowerComponent() - o1.getPowerComponent();
            }
        });

        // For each component
        for (String polyComponent : polyComponents) {

            // Find the groups using the regex given
            Matcher matcher = polynomialComponentPattern.matcher(polyComponent);

            // Check if match has been found (should have been)
            boolean matchFound = matcher.find();

            // Check if there are 4 groups and match was found - if not - throw an exception
            if (matchFound && matcher.groupCount() == 4) {

                // Get the separated strings
                String signComponent = matcher.group(1);
                String numberComponent = matcher.group(2);
                String xExpression = matcher.group(3);
                String powerComponent = matcher.group(4);

                // Parse the number value
                double numberAbsValue;
                if (numberComponent == null || numberComponent.isEmpty()) {
                    numberAbsValue = 1.0;
                } else {
                    numberAbsValue = Double.valueOf(numberComponent);
                }

                // Take the sign into account
                final double numberValue;
                if ("-".equals(signComponent)) {
                    numberValue = numberAbsValue * -1;
                } else {
                    numberValue = numberAbsValue;
                }

                // Parse the power value
                final int powerValue;
                if (powerComponent == null || powerComponent.isEmpty()) {
                    if ("x".equals(xExpression)) {
                        // There's single "x"
                        powerValue = 1;
                    } else if (xExpression == null || xExpression.isEmpty()) {
                        // There's no "x" expression
                        powerValue = 0;
                    } else {
                        throw new ParseException("Wrong structure.", 0);
                    }
                } else {
                    powerValue = Integer.valueOf(powerComponent);

                    if (powerValue == 0) {
                        throw new ParseException("Parser doesn't accept 0 as power value.", 0);
                    }
                }

                // Try to add newly parsed component to the set. If it fails, it
                // means that there are the same elements in the expression
                boolean success = components.add(new PolyComponent() {

                    @Override
                    public Integer getPowerComponent() {
                        return powerValue;
                    }

                    @Override
                    public Double getNumberComponent() {
                        return numberValue;
                    }
                });

                if (!success) {
                    throw new ParseException("The same argument used!", 0);
                }

            } else {
                throw new ParseException("Couldn't parse a component: " + polyComponent, 0);
            }
        }

        return components;
    }

    /**
     * Private interface used to ease handling polynomial components.
     * @author tomaszj
     */
    private interface PolyComponent {
        /** @return Coefficient for the polynomial component */
        Double getNumberComponent();

        /** @return Power value for the polynomial component */
        Integer getPowerComponent();
    }
}

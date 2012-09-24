/**
 */
package pl.org.tomaszjaneczko.mouproject.math.parsers.test;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import pl.org.tomaszjaneczko.mouproject.math.Polynomial;
import pl.org.tomaszjaneczko.mouproject.math.parsers.PolynomialParser;

/**
 * @author tomaszj
 *
 */
public class PolynomialParserTest {

    private PolynomialParser parser;

    @Before
    public void setUp() {
        parser = new PolynomialParser();
    }

    @Test
    public void testIfParsesConstant() throws ParseException {
        String expressionString = "1.0";
        Polynomial parsedPoly = parser.parseString(expressionString);
        assertEquals(parsedPoly, Polynomial.getSingularPolynomial());

        String expressionString2 = "0.0";
        Polynomial parsedPoly2 = parser.parseString(expressionString2);
        assertEquals(parsedPoly2, Polynomial.getZeroPolynomial());

        String expressionString3 = "-1.0";
        Polynomial parsedPoly3 = parser.parseString(expressionString3);
        assertEquals(parsedPoly3, new Polynomial(new Double[] {-1.0}));
    }

    @Test
    public void testIfParsesSimpleExpressions() throws ParseException {
        String expressionString = "x";
        Polynomial parsedPoly = parser.parseString(expressionString);
        assertEquals(parsedPoly, new Polynomial(new Double[] {1.0, 0.0}));

        String expressionString2 = "-x";
        Polynomial parsedPoly2 = parser.parseString(expressionString2);
        assertEquals(parsedPoly2, new Polynomial(new Double[] {-1.0, 0.0}));

        String expressionString3 = "1.0*x";
        Polynomial parsedPoly3 = parser.parseString(expressionString3);
        assertEquals(parsedPoly3, new Polynomial(new Double[] {1.0, 0.0}));

        String expressionString4 = "-1.0*x";
        Polynomial parsedPoly4 = parser.parseString(expressionString4);
        assertEquals(parsedPoly4, new Polynomial(new Double[] {-1.0, 0.0}));

        String expressionString5 = "-5.0*x";
        Polynomial parsedPoly5 = parser.parseString(expressionString5);
        assertEquals(parsedPoly5, new Polynomial(new Double[] {-5.0, 0.0}));
    }

    @Test
    public void testIfParsesBinomials() throws ParseException {
        String expressionString = "x+1";
        Polynomial parsedPoly = parser.parseString(expressionString);
        assertEquals(parsedPoly, new Polynomial(new Double[] {1.0, 1.0}));

        String expressionString2 = "-x+1";
        Polynomial parsedPoly2 = parser.parseString(expressionString2);
        assertEquals(parsedPoly2, new Polynomial(new Double[] {-1.0, 1.0}));

        String expressionString3 = "1.0*x-1.0";
        Polynomial parsedPoly3 = parser.parseString(expressionString3);
        assertEquals(parsedPoly3, new Polynomial(new Double[] {1.0, -1.0}));

        String expressionString4 = "-1.0*x+5.0";
        Polynomial parsedPoly4 = parser.parseString(expressionString4);
        assertEquals(parsedPoly4, new Polynomial(new Double[] {-1.0, 5.0}));

        String expressionString5 = "-5.0*x-5.0";
        Polynomial parsedPoly5 = parser.parseString(expressionString5);
        assertEquals(parsedPoly5, new Polynomial(new Double[] {-5.0, -5.0}));
    }

    @Test
    public void testIfParsesComplexPolynomials() throws ParseException {
        String expressionString = "x^2+x+1";
        Polynomial parsedPoly = parser.parseString(expressionString);
        assertEquals(parsedPoly, new Polynomial(new Double[] {1.0, 1.0, 1.0}));

        String expressionString2 = "-x^2+x+1";
        Polynomial parsedPoly2 = parser.parseString(expressionString2);
        assertEquals(parsedPoly2, new Polynomial(new Double[] {-1.0, 1.0, 1.0}));

        String expressionString3 = "1.0*x^2+1.0*x-1.0";
        Polynomial parsedPoly3 = parser.parseString(expressionString3);
        assertEquals(parsedPoly3, new Polynomial(new Double[] {1.0, 1.0, -1.0}));

        String expressionString4 = "-1.0*x^2-1.0*x+5.0";
        Polynomial parsedPoly4 = parser.parseString(expressionString4);
        assertEquals(parsedPoly4, new Polynomial(new Double[] {-1.0, -1.0, 5.0}));

        String expressionString5 = "-5.0*x^2-5.0*x-5.0";
        Polynomial parsedPoly5 = parser.parseString(expressionString5);
        assertEquals(parsedPoly5, new Polynomial(new Double[] {-5.0, -5.0, -5.0}));
    }

    @Test(expected = ParseException.class)
    public void testIfFailsOnErroneousInput() throws ParseException {
        String expressionString = "blargh";
        parser.parseString(expressionString);
    }

    @Test(expected = ParseException.class)
    public void testIfFailsOnMissingPlusAndMinusSigns() throws ParseException {
        String expressionString4 = "-1.0*x^21.0*x+5.0";
        parser.parseString(expressionString4);
    }

    public void testIfHandlesWhitespace() throws ParseException {
        String expressionString = "x^2 + x + 1";
        Polynomial parsedPoly = parser.parseString(expressionString);
        assertEquals(parsedPoly, new Polynomial(new Double[] {1.0, 1.0, 1.0}));

        String expressionString2 = "-x^2 + x + 1";
        Polynomial parsedPoly2 = parser.parseString(expressionString2);
        assertEquals(parsedPoly2, new Polynomial(new Double[] {-1.0, 1.0, 1.0}));

        String expressionString3 = "1.0*x^2 + 1.0*x - 1.0";
        Polynomial parsedPoly3 = parser.parseString(expressionString3);
        assertEquals(parsedPoly3, new Polynomial(new Double[] {1.0, 1.0, -1.0}));

        String expressionString4 = "-1.0 * x^2 - 1.0 * x + 5.0";
        Polynomial parsedPoly4 = parser.parseString(expressionString4);
        assertEquals(parsedPoly4, new Polynomial(new Double[] {-1.0, -1.0, 5.0}));

        String expressionString5 = "-5.0 * x^2- 5.0*x-5.0";
        Polynomial parsedPoly5 = parser.parseString(expressionString5);
        assertEquals(parsedPoly5, new Polynomial(new Double[] {-5.0, -5.0, -5.0}));
    }

}

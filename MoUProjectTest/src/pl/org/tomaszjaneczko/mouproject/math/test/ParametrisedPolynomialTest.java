package pl.org.tomaszjaneczko.mouproject.math.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Map;

import org.junit.Test;

import pl.org.tomaszjaneczko.mouproject.math.ParametrisedPolynomial;
import pl.org.tomaszjaneczko.mouproject.math.Polynomial;

public class ParametrisedPolynomialTest {

    @Test
    public void testGetParams() {
        String[] params = new String[] {"A", "B"}; // Ax + B
        ParametrisedPolynomial poly = new ParametrisedPolynomial(params);

        assertTrue(Arrays.equals(params, poly.getParams()));
    }

    @Test
    public void testGetParamValuesForDegree() {
        String[] params = new String[] {"A", "B", "C"}; // Ax^2 + Bx + C
        ParametrisedPolynomial poly = new ParametrisedPolynomial(params);

        Map<String, Double> x2degreeMap = poly.getParamValuesForDegree(2);
        assertEquals(new Double(1.0), x2degreeMap.get("A"));
        assertEquals(new Double(0.0), x2degreeMap.get("B"));
        assertEquals(new Double(0.0), x2degreeMap.get("C"));

        Map<String, Double> xDegreeMap = poly.getParamValuesForDegree(1);
        assertEquals(new Double(0.0), xDegreeMap.get("A"));
        assertEquals(new Double(1.0), xDegreeMap.get("B"));
        assertEquals(new Double(0.0), xDegreeMap.get("C"));

        Map<String, Double> noDegreeMap = poly.getParamValuesForDegree(0);
        assertEquals(new Double(0.0), noDegreeMap.get("A"));
        assertEquals(new Double(0.0), noDegreeMap.get("B"));
        assertEquals(new Double(1.0), noDegreeMap.get("C"));

    }

    @Test
    public void testGetParamValuesForDegreeAsArray() {
        String[] params = new String[] {"A", "B", "C"}; // Ax^2 + Bx + C
        ParametrisedPolynomial poly = new ParametrisedPolynomial(params);

        assertArrayEquals(new Double[] {1.0, 0.0, 0.0}, poly.getParamValuesForDegreeAsArray(2));
        assertArrayEquals(new Double[] {0.0, 1.0, 0.0}, poly.getParamValuesForDegreeAsArray(1));
        assertArrayEquals(new Double[] {0.0, 0.0, 1.0}, poly.getParamValuesForDegreeAsArray(0));
    }

    @Test
    public void testAdd() {
        String[] params = new String[] {"A", "B", "C"}; // Ax^2 + Bx + C
        ParametrisedPolynomial poly = new ParametrisedPolynomial(params);
        ParametrisedPolynomial poly2 = new ParametrisedPolynomial(params);

        ParametrisedPolynomial poly3 = poly.add(poly2);

        assertArrayEquals(new Double[] {2.0, 0.0, 0.0}, poly3.getParamValuesForDegreeAsArray(2));
        assertArrayEquals(new Double[] {0.0, 2.0, 0.0}, poly3.getParamValuesForDegreeAsArray(1));
        assertArrayEquals(new Double[] {0.0, 0.0, 2.0}, poly3.getParamValuesForDegreeAsArray(0));
    }

    @Test
    public void testMultiplyByScalar() {
        String[] params = new String[] {"A", "B", "C"}; // Ax^2 + Bx + C
        ParametrisedPolynomial poly = new ParametrisedPolynomial(params);

        ParametrisedPolynomial poly3 = poly.multiplyByScalar(18.0);

        assertArrayEquals(new Double[] {18.0, 0.0, 0.0}, poly3.getParamValuesForDegreeAsArray(2));
        assertArrayEquals(new Double[] {0.0, 18.0, 0.0}, poly3.getParamValuesForDegreeAsArray(1));
        assertArrayEquals(new Double[] {0.0, 0.0, 18.0}, poly3.getParamValuesForDegreeAsArray(0));
    }

    @Test
    public void testMultiplyBySingularPolynomial() {
        // Test singular case - multiplying by 1
        Polynomial simplePoly = Polynomial.getSingularPolynomial();
        String[] params = new String[] {"A", "B"}; // Ax^2 + Bx + C
        ParametrisedPolynomial paramPoly1 = new ParametrisedPolynomial(params);

        ParametrisedPolynomial resultPoly1 = paramPoly1.multiplyByPolynomial(simplePoly);

        assertArrayEquals(new Double[] {1.0, 0.0}, resultPoly1.getParamValuesForDegreeAsArray(1));
        assertArrayEquals(new Double[] {0.0, 1.0}, resultPoly1.getParamValuesForDegreeAsArray(0));

    }

    @Test
    public void testMultiplyBySingleArgumentBinomial() {
        // Let's multiply by x
        Polynomial poly = Polynomial.getSingleArgumentPolynomialOfDegree(1);
        String[] params = new String[] {"A", "B"}; // Ax^2 + Bx + C
        ParametrisedPolynomial paramPoly1 = new ParametrisedPolynomial(params);

        ParametrisedPolynomial resultPoly1 = paramPoly1.multiplyByPolynomial(poly);

        assertArrayEquals(new Double[] {1.0, 0.0}, resultPoly1.getParamValuesForDegreeAsArray(2));
        assertArrayEquals(new Double[] {0.0, 1.0}, resultPoly1.getParamValuesForDegreeAsArray(1));
        assertArrayEquals(new Double[] {0.0, 0.0}, resultPoly1.getParamValuesForDegreeAsArray(0));
    }

    @Test
    public void testMultiplyByBinomial() {
        // Let's multiply by x
        Polynomial poly = new Polynomial(new Double[] {2.0, 1.0});
        String[] params = new String[] {"A", "B"}; // Ax^2 + Bx + C
        ParametrisedPolynomial paramPoly1 = new ParametrisedPolynomial(params);

        ParametrisedPolynomial resultPoly1 = paramPoly1.multiplyByPolynomial(poly);

        assertArrayEquals(new Double[] {2.0, 0.0}, resultPoly1.getParamValuesForDegreeAsArray(2));
        assertArrayEquals(new Double[] {1.0, 2.0}, resultPoly1.getParamValuesForDegreeAsArray(1));
        assertArrayEquals(new Double[] {0.0, 1.0}, resultPoly1.getParamValuesForDegreeAsArray(0));
    }

    @Test
    public void testGetParamMatrix() {
        String[] params = new String[] {"A"};

        ParametrisedPolynomial simplePoly = new ParametrisedPolynomial(params);
        Double[][] paramMatrix = simplePoly.getParamMatrix();
        assertTrue(paramMatrix[0][0] == 1.0);
        assertTrue(paramMatrix.length == 1);
        assertTrue(paramMatrix[0].length == 1);

        String[] params2 = new String[] {"A", "B"};

        ParametrisedPolynomial poly = new ParametrisedPolynomial(params2);
        Double[][] paramMatrix2 = poly.getParamMatrix();
        assertTrue(paramMatrix2[0][0] == 1.0);
        assertTrue(paramMatrix2[0][1] == 0.0);
        assertTrue(paramMatrix2[1][0] == 0.0);
        assertTrue(paramMatrix2[1][1] == 1.0);
        assertTrue(paramMatrix2.length == 2);
        assertTrue(paramMatrix2[0].length == 2);


    }

    @Test
    public void testSimpleDifferentiation() {
        String[] params = new String[] {"A"};

        ParametrisedPolynomial simplePoly = new ParametrisedPolynomial(params);
        ParametrisedPolynomial diffedPoly = simplePoly.differentiate();
        Double[][] paramMatrix = diffedPoly.getParamMatrix();

        assertEquals(new Double(0.0), paramMatrix[0][0]);
    }

    @Test
    public void testDifferentiation() {
        String[] params = new String[] {"A", "B"};

        ParametrisedPolynomial simplePoly = new ParametrisedPolynomial(params);
        ParametrisedPolynomial diffedPoly = simplePoly.differentiate();
        Double[][] paramMatrix = diffedPoly.getParamMatrix();

        assertEquals(new Double(0.0), paramMatrix[0][0]);
        assertEquals(new Double(0.0), paramMatrix[0][1]);
        assertEquals(new Double(1.0), paramMatrix[1][0]);
        assertEquals(new Double(0.0), paramMatrix[1][1]);
    }

    @Test
    public void testBiggerDifferentiation() {
        String[] params = new String[] {"A", "B", "C"};

        ParametrisedPolynomial simplePoly = new ParametrisedPolynomial(params);
        ParametrisedPolynomial diffedPoly = simplePoly.differentiate();
        Double[][] paramMatrix = diffedPoly.getParamMatrix();

        assertEquals(new Double(2.0), paramMatrix[1][0]); // 2*A*x
        assertEquals(new Double(1.0), paramMatrix[2][1]); // B
    }
}

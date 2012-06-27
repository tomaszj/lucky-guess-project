/**
 */
package pl.org.tomaszjaneczko.mouproject.math.ode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pl.org.tomaszjaneczko.mouproject.math.ComplexNumber;
import pl.org.tomaszjaneczko.mouproject.math.Exponential;
import pl.org.tomaszjaneczko.mouproject.math.Polynomial;
import pl.org.tomaszjaneczko.mouproject.math.SineAndCosine;

/**
 * @author tomaszj
 *
 */
public class SolutionBasis {

    /** Solution components in the solution basis. */
    private final Set<EquationComponent> solutionComponents;


    /**
     * Default constructor.
     * @param roots used to determine the solution components.
     */
    public SolutionBasis(final ComplexNumber[] roots) {
        solutionComponents = new HashSet<EquationComponent>();

        findSolutionComponents(roots);
    }

    /**
     * No-arg constructor.
     */
    public SolutionBasis() {
        solutionComponents = new HashSet<EquationComponent>();
    }

    /**
     * @return the solutionComponents
     */
    public final Set<EquationComponent> getSolutionComponents() {
        return solutionComponents;
    }

    /**
     * Method finds solution components based on roots.
     * @param roots of a characteristic equation
     */
    private void findSolutionComponents(final ComplexNumber[] roots) {
        // Reduce the roots

        Map<ComplexNumber, RootWithDegree> rootsMap = new HashMap<ComplexNumber, RootWithDegree>();

        for (ComplexNumber root : roots) {
            RootWithDegree rootWithDegree = rootsMap.get(root);
            if (rootWithDegree == null) {
                rootsMap.put(root, new RootWithDegree(root));
            } else {
                rootWithDegree.increaseDegree();
            }

        }

        Set<RootWithDegree> rootWithDegrees = new HashSet<RootWithDegree>(rootsMap.values());

        for (RootWithDegree root : rootWithDegrees) {
            ComplexNumber rootValue = root.getRootValue();

            for (int index = 0; index < root.getRootDegree(); index++) {
                // Create an array with coefficients of appropriate size - to fit all coeffs.
                Double[] polynomialCoeffs = new Double[index + 1];
                polynomialCoeffs[0] = 1.0;
                for (int i = 1; i < polynomialCoeffs.length; i++) {
                    polynomialCoeffs[i] = 0.0;
                }

                Polynomial poly = new Polynomial(polynomialCoeffs);
                Exponential exponential = new Exponential(rootValue.getRealPart());

                SineAndCosine sineAndCosine;
                double trigCoefficient = rootValue.getImaginaryPart();
                if (trigCoefficient == 0) {
                    sineAndCosine = SineAndCosine.getSingularSineAndCosine();
                } else if (trigCoefficient > 0) {
                    // Associate with sine
                    sineAndCosine = new SineAndCosine(trigCoefficient,
                            Polynomial.getSingularPolynomial(),
                            Polynomial.getZeroPolynomial());
                } else {
                    // Associate with cosine
                    sineAndCosine = new SineAndCosine(Math.abs(trigCoefficient),
                            Polynomial.getZeroPolynomial(),
                            Polynomial.getSingularPolynomial());
                }

                solutionComponents.add(new EquationComponent(poly, exponential, sineAndCosine));
            }
        }
    }

    /**
     * Class representing a poly's root with its' degree.
     * @author tomaszj
     *
     */
    private class RootWithDegree {

        /** Root value. */
        private ComplexNumber rootValue;

        /** The degree of the root. */
        private int rootDegree = 1;

        /**
         * Default constructor.
         * @param rootVal root value
         */
        public RootWithDegree(final ComplexNumber rootVal) {
            rootValue = rootVal;
        }

        /**
         * Method which increases the degree by one.
         */
        public void increaseDegree() {
            rootDegree++;
        }

        /**
         * @return the rootValue
         */
        public final ComplexNumber getRootValue() {
            return rootValue;
        }

        /**
         * @return the rootDegree
         */
        public final int getRootDegree() {
            return rootDegree;
        }

    }
}

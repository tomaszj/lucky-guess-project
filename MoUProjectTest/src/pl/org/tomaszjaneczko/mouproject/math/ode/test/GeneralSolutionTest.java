package pl.org.tomaszjaneczko.mouproject.math.ode.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;

import pl.org.tomaszjaneczko.mouproject.math.ode.GeneralSolution;
import pl.org.tomaszjaneczko.mouproject.math.ode.SolutionBasis;
import pl.org.tomaszjaneczko.mouproject.math.ode.EquationComponent;

public class GeneralSolutionTest {

    @Test
    public void testGeneralSolutionRendering() {

        final EquationComponent component1 = SolutionComponentTest.getExampleSolutionComponent();
        final EquationComponent component2 = SolutionComponentTest.getDifferentSolutionComponent();

        SolutionBasis basis = new SolutionBasis() {
            @Override
            public Set<EquationComponent> getSolutionComponents() {
                Set<EquationComponent> components = new HashSet<EquationComponent>();
                components.add(component1);
                components.add(component2);
                return components;
            };
        };

        GeneralSolution generalSolution = new GeneralSolution(basis);
        String generalSolutionString = generalSolution.toString();

        assertTrue(generalSolutionString.contains("(1.0*x+1.0)*e^(5.0*x)*((1.0)*sin(3.0*x)+(1.0)*cos(3.0*x))"));
        assertTrue(generalSolutionString.contains("C0*"));
        assertTrue(generalSolutionString.contains("1.0*e^(3.0*x)*((1.0)*sin(3.0*x)+(1.0)*cos(3.0*x))"));
        assertTrue(generalSolutionString.contains("C1*"));
    }

}

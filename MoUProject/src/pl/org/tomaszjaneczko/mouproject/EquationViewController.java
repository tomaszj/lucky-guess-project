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
package pl.org.tomaszjaneczko.mouproject;

import java.text.ParseException;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import pl.org.tomaszjaneczko.mouproject.math.Polynomial;
import pl.org.tomaszjaneczko.mouproject.math.ode.CalculationFailedException;
import pl.org.tomaszjaneczko.mouproject.math.ode.LuckyGuessSolver;
import pl.org.tomaszjaneczko.mouproject.math.parsers.PolynomialParser;

/**
 * Class responsible for management of Equation View.
 * @author tomaszj
 */
public class EquationViewController {

    /** View holding all the view information. */
    private EquationView view;

    /**
     * Method called when the View Controller is created.
     * @param context SWT context
     */
    public final void create(final Shell context) {
        view = new EquationView(context);

        view.getSolveButton().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent event) {
                String d2y = view.getD2yText().getText();
                String dy = view.getDyText().getText();
                String y = view.getyText().getText();
                String polynomial = view.getPolynomialText().getText();
                String exponentialCoefficient = view.getExpText().getText();

                if ("".equals(d2y)) {
                    d2y = "0.0";
                }
                if ("".equals(dy)) {
                    dy = "0.0";
                }
                if ("".equals(y)) {
                    y = "0.0";
                }
                if ("".equals(polynomial)) {
                    polynomial = "0.0";
                }
                if ("".equals(exponentialCoefficient)) {
                    exponentialCoefficient = "0.0";
                }

                view.getD2yText().setText(d2y);
                view.getDyText().setText(dy);
                view.getyText().setText(y);
                view.getPolynomialText().setText(polynomial);
                view.getExpText().setText(exponentialCoefficient);

                try {
                    Double d2yValue = Double.valueOf(d2y);
                    Double dyValue = Double.valueOf(dy);
                    Double yValue = Double.valueOf(y);

                    Double exponentialCoeff = Double.valueOf(exponentialCoefficient);
                    Polynomial independentPolynomial = new PolynomialParser().parseString(polynomial);

                    if (dyValue == 0.0 && d2yValue == 0.0) {
                        MessageBox alertDialog = new MessageBox(context);
                        alertDialog.setMessage("Co najmniej jeden współczynnik przy pochodnej powinien być niezerowy!");
                        alertDialog.setText("Błąd!");

                        alertDialog.open();
                        return;
                    }

                    // Create the solver
                    LuckyGuessSolver odeSolver = new LuckyGuessSolver(
                            new Double[] {d2yValue, dyValue}, yValue,
                            independentPolynomial, exponentialCoeff);
                    odeSolver.solve();

                    String result = odeSolver.toString();
                    view.getResultText().setText(result);

                } catch (NumberFormatException e) {
                    MessageBox alertDialog = new MessageBox(context);
                    alertDialog.setMessage("Błędny format liczb");
                    alertDialog.setText("Błąd!");

                    alertDialog.open();
                    return;
                } catch (CalculationFailedException e) {
                    MessageBox alertDialog = new MessageBox(context);

                    String reason = null;

                    switch (e.getReason()) {
                    case NO_SOLVABLE_PARAMETER_MATRIX_FOUND:
                        reason = "Nie znaleziono rozwiązywalnej macierzy parametrów. "
                                + "Skontaktuj się z autorem aby usprawnić program.";
                        break;

                    default:
                        reason = "Nastąpił nieznany problem. Skontaktuj się z autorem, aby naprawić błąd.";
                        break;
                    }

                    alertDialog.setMessage(reason);
                    alertDialog.setText("Błąd!");

                    alertDialog.open();
                    return;

                } catch (ParseException e) {
                    MessageBox alertDialog = new MessageBox(context);

                    alertDialog.setMessage("Wielomian ma błędną postać. Sprawdź go ponownie i popraw.");
                    alertDialog.setText("Błąd!");

                    alertDialog.open();
                    return;

                }

            }
        });
    }

}

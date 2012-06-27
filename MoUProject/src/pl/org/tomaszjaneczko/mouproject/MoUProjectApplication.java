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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;

/**
 * Main class responsible for setting up the SWT UI.
 * @author tomaszj
 */
public class MoUProjectApplication {

    /** Shell being the main context of the application. */
    private Shell shell;

    /** Main view controller used in the application. */
    private EquationViewController viewController;

    /**
     * Launch the application.
     * @param args for the application
     */
    public static void main(final String[] args) {
        try {
            MoUProjectApplication window = new MoUProjectApplication();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Open the window.
     */
    public final void open() {
        Display.setAppName("Metoda Przewidywań");
        Display display = Display.getDefault();
        createWindow();

        // Create the View Controller
        viewController = new EquationViewController();
        viewController.create(shell);

        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * Create contents of the window.
     */
    protected final void createWindow() {
        shell = new Shell();
        shell.setMinimumSize(new Point(541, 161));
        shell.setSize(541, 161);
        shell.setText("Metoda Przewidywań");
        shell.setLayout(new FormLayout());

        Menu menu = new Menu(shell, SWT.BAR);
        shell.setMenuBar(menu);

        MenuItem mntmAbout = new MenuItem(menu, SWT.CASCADE);
        mntmAbout.setText("O programie");

        Menu menu2 = new Menu(mntmAbout);
        mntmAbout.setMenu(menu2);

        MenuItem mntmInformacje = new MenuItem(menu2, SWT.NONE);
        mntmInformacje.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                MessageBox messageBox = new MessageBox(shell);
                messageBox.setMessage("Projekt: Rozwiązywanie równań różniczkowych z użyciem metody przewidywań.\n\n"
                        + "Autor: Tomasz Janeczko 2012.");
                messageBox.setText("Informacje");

                messageBox.open();
            }
        });
        mntmInformacje.setText("Informacje");
    }
}

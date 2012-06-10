package pl.org.tomaszjaneczko.mouproject;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.graphics.Point;

public class MoUProjectApplication {

    protected Shell shell;
    private EquationViewController viewController;

    /**
     * Launch the application.
     * @param args
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
        shell.setMinimumSize(new Point(423, 159));
        shell.setSize(423, 159);
        shell.setText("Metoda Przewidywań");
        shell.setLayout(new FormLayout());
    }
}

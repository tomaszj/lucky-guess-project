package pl.org.tomaszjaneczko.mouproject;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class MoUProject {

    protected Shell shell;
    private Text text;
    private Text text_1;
    private Text text_2;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(final String[] args) {
        try {
            MoUProject window = new MoUProject();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Open the window.
     */
    public void open() {
        Display display = Display.getDefault();
        createContents();
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
    protected void createContents() {
        shell = new Shell();
        shell.setSize(415, 125);
        shell.setText("Metoda Przewidywań");

        text = new Text(shell, SWT.BORDER);
        text.setBounds(10, 10, 180, 19);

        text_1 = new Text(shell, SWT.BORDER);
        text_1.setBounds(30, 70, 353, 19);

        Label label = new Label(shell, SWT.NONE);
        label.setAlignment(SWT.CENTER);
        label.setBounds(196, 13, 21, 14);
        label.setText("=");

        text_2 = new Text(shell, SWT.BORDER);
        text_2.setBounds(223, 10, 180, 19);

        Button solveButton = new Button(shell, SWT.NONE);
        solveButton.setBounds(160, 35, 94, 28);
        solveButton.setText("Rozwiąż!");
        solveButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent arg0) {
                MessageBox messageBox = new MessageBox(shell);
                messageBox.setText("Test");
                messageBox.setMessage("Test 2");

                messageBox.open();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent arg0) {
                // TODO Auto-generated method stub

            }
        });
    }
}

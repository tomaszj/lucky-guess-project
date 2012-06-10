/**
 * 
 */
package pl.org.tomaszjaneczko.mouproject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author tomaszj
 * 
 */
public class EquationView {
    /** Result Text View. */
    private Text resultText;
    private Text text_2;

    /** Second derivative Text View. */
    private Text d2yText;
    /** First derivative Text View. */
    private Text dyText;
    /** Non-derivative component Text View. */
    private Text yText;
    /** Solve button. */
    private Button solveButton;
    /** EquationGroup. */
    private Group grpRwnanie;
    /** Form data. */
    private FormData fd_resultText;

    /**
     * Constructor for the view.
     * @param context SWT API accessor.
     */
    public EquationView(final Shell context) {
        resultText = new Text(context, SWT.BORDER);
        fd_resultText = new FormData();
        resultText.setLayoutData(fd_resultText);

        solveButton = new Button(context, SWT.CENTER);
        fd_resultText.top = new FormAttachment(solveButton, 6);
        FormData fd_solveButton = new FormData();
        fd_solveButton.width = 170;
        fd_solveButton.left = new FormAttachment(0, 162);
        fd_solveButton.right = new FormAttachment(100, -166);
        solveButton.setLayoutData(fd_solveButton);
        solveButton.setText("Rozwiąż!");

        grpRwnanie = new Group(context, SWT.NONE);
        fd_resultText.right = new FormAttachment(grpRwnanie, -20, SWT.RIGHT);
        fd_solveButton.top = new FormAttachment(grpRwnanie, 6);
        RowLayout rl_grpRwnanie = new RowLayout(SWT.HORIZONTAL);
        rl_grpRwnanie.marginLeft = 5;
        rl_grpRwnanie.marginTop = 8;
        grpRwnanie.setLayout(rl_grpRwnanie);
        FormData fd_grpRwnanie = new FormData();
        fd_grpRwnanie.bottom = new FormAttachment(0, 65);
        fd_grpRwnanie.right = new FormAttachment(0, 412);
        fd_grpRwnanie.top = new FormAttachment(0, 10);
        fd_grpRwnanie.left = new FormAttachment(0, 10);
        grpRwnanie.setLayoutData(fd_grpRwnanie);
        grpRwnanie.setText("Równanie");

        d2yText = new Text(grpRwnanie, SWT.BORDER);

        Label lblY = new Label(grpRwnanie, SWT.NONE);
        lblY.setText("y''");

        Label label_1 = new Label(grpRwnanie, SWT.NONE);
        label_1.setText("+");

        dyText = new Text(grpRwnanie, SWT.BORDER);

        Label lblY_1 = new Label(grpRwnanie, SWT.NONE);
        lblY_1.setText("y'");

        Label label_2 = new Label(grpRwnanie, SWT.NONE);
        label_2.setText("+");

        yText = new Text(grpRwnanie, SWT.BORDER);

        Label lblY_2 = new Label(grpRwnanie, SWT.NONE);
        lblY_2.setText("y");

        Label label = new Label(grpRwnanie, SWT.NONE);
        label.setAlignment(SWT.CENTER);
        label.setText("=");

        text_2 = new Text(grpRwnanie, SWT.BORDER);

        Label lblY_3 = new Label(context, SWT.NONE);
        fd_resultText.left = new FormAttachment(lblY_3, 6);
        FormData fd_lblY_3 = new FormData();
        fd_lblY_3.top = new FormAttachment(grpRwnanie, 43);
        fd_lblY_3.left = new FormAttachment(0, 10);
        lblY_3.setLayoutData(fd_lblY_3);
        lblY_3.setText("y =");
    }

    /**
     * @return the resultText
     */
    public final Text getResultText() {
        return resultText;
    }

    /**
     * @return the text_2
     */
    public final Text getText_2() {
        return text_2;
    }

    /**
     * @return the d2yText
     */
    public final Text getD2yText() {
        return d2yText;
    }

    /**
     * @return the dyText
     */
    public final Text getDyText() {
        return dyText;
    }

    /**
     * @return the yText
     */
    public final Text getyText() {
        return yText;
    }

    /**
     * @return the solveButton
     */
    public final Button getSolveButton() {
        return solveButton;
    }


}

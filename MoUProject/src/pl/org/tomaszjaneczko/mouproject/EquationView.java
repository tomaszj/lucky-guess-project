/**
 */
package pl.org.tomaszjaneczko.mouproject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author tomaszj
 */
public class EquationView {
    /** Result Text View. */
    private Text resultText;
    /** Polynomial Text View. */
    private Text polynomialText;
    /** Exponential coefficient Text View. */
    private Text expText;
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


    /**
     * Constructor for the view.
     * @param context SWT API accessor.
     */
    public EquationView(final Shell context) {
        resultText = new Text(context, SWT.BORDER);
        resultText.setEditable(false);
        FormData fdResultText = new FormData();
        resultText.setLayoutData(fdResultText);

        solveButton = new Button(context, SWT.CENTER);
        fdResultText.top = new FormAttachment(solveButton, 6);
        FormData fdSolveButton = new FormData();
        fdSolveButton.right = new FormAttachment(0, 375);
        fdSolveButton.left = new FormAttachment(0, 162);
        solveButton.setLayoutData(fdSolveButton);
        solveButton.setText("Rozwiąż!");

        grpRwnanie = new Group(context, SWT.NONE);
        fdResultText.left = new FormAttachment(grpRwnanie, -493);
        fdResultText.right = new FormAttachment(grpRwnanie, 0, SWT.RIGHT);
        fdSolveButton.top = new FormAttachment(0, 71);
        RowLayout rlGrpRwnanie = new RowLayout(SWT.HORIZONTAL);
        rlGrpRwnanie.marginLeft = 5;
        rlGrpRwnanie.marginTop = 8;
        grpRwnanie.setLayout(rlGrpRwnanie);
        FormData fdGrpRwnanie = new FormData();
        fdGrpRwnanie.bottom = new FormAttachment(solveButton, -6);
        fdGrpRwnanie.top = new FormAttachment(0, 10);
        fdGrpRwnanie.left = new FormAttachment(0, 10);
        fdGrpRwnanie.right = new FormAttachment(0, 531);
        grpRwnanie.setLayoutData(fdGrpRwnanie);
        grpRwnanie.setText("Równanie");

        d2yText = new Text(grpRwnanie, SWT.BORDER);
        d2yText.setToolTipText("Wpisz stałą wartość.");
        d2yText.setLayoutData(new RowData(32, SWT.DEFAULT));

        Label lblY = new Label(grpRwnanie, SWT.NONE);
        lblY.setText("y''");

        Label label1 = new Label(grpRwnanie, SWT.NONE);
        label1.setText("+");

        dyText = new Text(grpRwnanie, SWT.BORDER);
        dyText.setToolTipText("Wpisz stałą wartość.");
        dyText.setLayoutData(new RowData(32, SWT.DEFAULT));

        Label lblY1 = new Label(grpRwnanie, SWT.NONE);
        lblY1.setText("y'");

        Label label2 = new Label(grpRwnanie, SWT.NONE);
        label2.setText("+");

        yText = new Text(grpRwnanie, SWT.BORDER);
        yText.setToolTipText("Wpisz stałą wartość.");
        yText.setLayoutData(new RowData(32, SWT.DEFAULT));

        Label lblY2 = new Label(grpRwnanie, SWT.NONE);
        lblY2.setText("y");

        Label label = new Label(grpRwnanie, SWT.NONE);
        label.setAlignment(SWT.CENTER);
        label.setText("=");

        polynomialText = new Text(grpRwnanie, SWT.BORDER);
        polynomialText.setToolTipText("Wpisz wielomian o postaci a*x^m{+|-}b*x^m{+|-} … . Przykład: 2*x^2-10*x+5.5");
        polynomialText.setLayoutData(new RowData(126, SWT.DEFAULT));

        Label lblY3 = new Label(context, SWT.NONE);
        FormData fdLblY3 = new FormData();
        fdLblY3.top = new FormAttachment(grpRwnanie, 43);

        Label lblXExp = new Label(grpRwnanie, SWT.NONE);
        lblXExp.setFont(SWTResourceManager.getFont("Lucida Grande", 16, SWT.NORMAL));
        lblXExp.setLayoutData(new RowData(SWT.DEFAULT, 22));
        lblXExp.setText("x exp(");

        expText = new Text(grpRwnanie, SWT.BORDER);
        expText.setToolTipText("Wpisz stałą wartość.");
        expText.setLayoutData(new RowData(32, SWT.DEFAULT));

        Label lblx = new Label(grpRwnanie, SWT.NONE);
        lblx.setText("*x)");
        lblx.setFont(SWTResourceManager.getFont("Lucida Grande", 16, SWT.NORMAL));
        fdLblY3.left = new FormAttachment(0, 10);
        lblY3.setLayoutData(fdLblY3);
        lblY3.setText("y =");

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
    public final Text getPolynomialText() {
        return polynomialText;
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

    /**
     * @return the expText
     */
    public final Text getExpText() {
        return expText;
    }
}

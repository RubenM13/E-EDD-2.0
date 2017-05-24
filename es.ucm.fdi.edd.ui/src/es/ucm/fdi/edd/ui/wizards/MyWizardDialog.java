package es.ucm.fdi.edd.ui.wizards;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

public class MyWizardDialog extends WizardDialog {

	/**
	 * Creates a new wizard dialog for the given wizard.
	 * It enables the minimized button. 
	 * 
	 * @param parentShell the parent shell
	 * @param newWizard the wizard this dialog is working on
	 */
	public MyWizardDialog(Shell parentShell, IWizard newWizard) {
		super(parentShell, newWizard);
		setShellStyle(SWT.CLOSE | SWT.MIN | SWT.MAX | SWT.TITLE | SWT.BORDER
				| SWT.APPLICATION_MODAL | SWT.RESIZE | getDefaultOrientation());
	}
}

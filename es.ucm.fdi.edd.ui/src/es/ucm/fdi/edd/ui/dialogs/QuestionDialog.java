package es.ucm.fdi.edd.ui.dialogs;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;

import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangMessage;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangQuestion;
import es.ucm.fdi.edd.core.erlang.concurrent.model.ErlangTreeNode;

public class QuestionDialog extends ApplicationWindow {

	private String title;
	private ErlangMessage message;
	private List<QuestionTabDialog> tabs;
	private TabFolder tabFolder;

	public QuestionDialog(Shell parentShell, ErlangMessage message, String title) {
		super(parentShell);
		this.message = message;
		this.tabs = new LinkedList<QuestionTabDialog>();
		this.title = title;
	}

	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(this.title);
	}

	protected Control createContents(Composite parent) {
		this.tabFolder = new TabFolder(parent, SWT.NONE);
		// Se crean una pestaña para cada pregunta
	    for (ErlangTreeNode node : this.message.getNodes()) {
	    	ErlangQuestion question = node.getQuestion();
	    	QuestionTabDialog tab = new QuestionTabDialog(this, tabFolder, SWT.NONE, question, String.valueOf(node.getId()));
	    	this.tabs.add(tab);
	    }
	    this.activateNextQuestion();
	    this.tabFolder.setSelection(0);
		return super.createContents(parent);
	}

	void activateNextQuestion() {
		boolean seguir = true;
		for (int i = 0; i < this.tabs.size() && seguir;i++) {
			this.tabs.get(i).enabled();
		}
	}

	public int getMessagePos() {
		return this.message.getPos();
	}
}

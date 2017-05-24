package es.ucm.fdi.emf.model.ed2.utils;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.ui.IMemento;

public class URIEditorInputProxy extends URIEditorInput {

	protected TransactionalEditingDomain editingDomain;
	
	public URIEditorInputProxy(URI uri, TransactionalEditingDomain domain) {
		super(uri);
		this.editingDomain = domain;
	}

	public URIEditorInputProxy(IMemento memento, TransactionalEditingDomain domain) {
		super(memento);
		this.editingDomain = domain;
	}

	public URIEditorInputProxy(URI uri, String name, TransactionalEditingDomain domain) {
		super(uri, name);
		this.editingDomain = domain;
	}
	
	public TransactionalEditingDomain getEditingDomain() {
		return editingDomain;
	}
}
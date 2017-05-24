package es.ucm.fdi.gmf.edd.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;

import es.ucm.fdi.gmf.edd.diagram.providers.EddElementTypes;

/**
 * @generated
 */
public class TreeElementLinksItemSemanticEditPolicy extends
		EddBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public TreeElementLinksItemSemanticEditPolicy() {
		super(EddElementTypes.TreeElementLinks_4001);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return getGEFWrapper(new DestroyReferenceCommand(req));
	}

}

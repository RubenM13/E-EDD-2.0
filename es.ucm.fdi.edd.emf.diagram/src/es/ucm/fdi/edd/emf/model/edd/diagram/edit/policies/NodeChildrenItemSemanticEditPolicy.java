package es.ucm.fdi.edd.emf.model.edd.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;

import es.ucm.fdi.edd.emf.model.edd.diagram.providers.EddElementTypes;

/**
 * @generated
 */
public class NodeChildrenItemSemanticEditPolicy extends
		EddBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public NodeChildrenItemSemanticEditPolicy() {
		super(EddElementTypes.NodeChildren_4001);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return getGEFWrapper(new DestroyReferenceCommand(req));
	}

}

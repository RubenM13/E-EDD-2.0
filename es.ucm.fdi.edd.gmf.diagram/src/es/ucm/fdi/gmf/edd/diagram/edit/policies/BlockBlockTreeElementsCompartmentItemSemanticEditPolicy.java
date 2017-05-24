package es.ucm.fdi.gmf.edd.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;

import es.ucm.fdi.gmf.edd.diagram.edit.commands.TreeElementCreateCommand;
import es.ucm.fdi.gmf.edd.diagram.providers.EddElementTypes;

/**
 * @generated
 */
public class BlockBlockTreeElementsCompartmentItemSemanticEditPolicy extends
		EddBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public BlockBlockTreeElementsCompartmentItemSemanticEditPolicy() {
		super(EddElementTypes.Block_3001);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (EddElementTypes.TreeElement_3002 == req.getElementType()) {
			return getGEFWrapper(new TreeElementCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated NOT
	 */
	protected Command getSemanticCommand(IEditCommandRequest request) {
		// FIXME Joel
		if (request instanceof DestroyRequest) {
			return null;
		}
		return super.getSemanticCommand(request);
	}
}

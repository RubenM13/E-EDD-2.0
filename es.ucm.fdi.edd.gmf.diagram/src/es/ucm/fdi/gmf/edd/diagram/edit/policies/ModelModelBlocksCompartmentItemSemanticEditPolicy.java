package es.ucm.fdi.gmf.edd.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import es.ucm.fdi.gmf.edd.diagram.edit.commands.BlockCreateCommand;
import es.ucm.fdi.gmf.edd.diagram.providers.EddElementTypes;

/**
 * @generated
 */
public class ModelModelBlocksCompartmentItemSemanticEditPolicy extends
		EddBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ModelModelBlocksCompartmentItemSemanticEditPolicy() {
		super(EddElementTypes.Model_2001);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (EddElementTypes.Block_3001 == req.getElementType()) {
			return getGEFWrapper(new BlockCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}

package es.ucm.fdi.edd.emf.model.edd.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

import es.ucm.fdi.edd.emf.model.edd.diagram.edit.commands.LeafCreateCommand;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.commands.NodeCreateCommand;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.commands.TreeElementCreateCommand;
import es.ucm.fdi.edd.emf.model.edd.diagram.providers.EddElementTypes;

/**
 * @generated
 */
public class EDDItemSemanticEditPolicy extends EddBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public EDDItemSemanticEditPolicy() {
		super(EddElementTypes.EDD_1000);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (EddElementTypes.TreeElement_2003 == req.getElementType()) {
			return getGEFWrapper(new TreeElementCreateCommand(req));
		}
		if (EddElementTypes.Node_2001 == req.getElementType()) {
			return getGEFWrapper(new NodeCreateCommand(req));
		}
		if (EddElementTypes.Leaf_2002 == req.getElementType()) {
			return getGEFWrapper(new LeafCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
				.getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends
			DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(
				TransactionalEditingDomain editingDomain,
				DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req
					.getElementsToBeDuplicated(), req
					.getAllDuplicatedElementsMap());
		}

	}

}

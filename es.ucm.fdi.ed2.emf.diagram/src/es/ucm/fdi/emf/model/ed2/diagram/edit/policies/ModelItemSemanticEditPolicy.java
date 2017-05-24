package es.ucm.fdi.emf.model.ed2.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

import es.ucm.fdi.emf.model.ed2.diagram.edit.commands.ED2CreateCommand;
import es.ucm.fdi.emf.model.ed2.diagram.edit.commands.LeafCreateCommand;
import es.ucm.fdi.emf.model.ed2.diagram.edit.commands.NodeCreateCommand;
import es.ucm.fdi.emf.model.ed2.diagram.providers.Ed2ElementTypes;

/**
 * @generated
 */
public class ModelItemSemanticEditPolicy extends Ed2BaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ModelItemSemanticEditPolicy() {
		super(Ed2ElementTypes.Model_1000);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (Ed2ElementTypes.ED2_2008 == req.getElementType()) {
			return getGEFWrapper(new ED2CreateCommand(req));
		}
		if (Ed2ElementTypes.Node_2006 == req.getElementType()) {
			return getGEFWrapper(new NodeCreateCommand(req));
		}
		if (Ed2ElementTypes.Leaf_2007 == req.getElementType()) {
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

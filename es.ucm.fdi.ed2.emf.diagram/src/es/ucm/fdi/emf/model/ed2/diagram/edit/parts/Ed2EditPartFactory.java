package es.ucm.fdi.emf.model.ed2.diagram.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.directedit.locator.CellEditorLocatorAccess;

import es.ucm.fdi.emf.model.ed2.diagram.part.Ed2VisualIDRegistry;

/**
 * @generated
 */
public class Ed2EditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (Ed2VisualIDRegistry.getVisualID(view)) {

			case ModelEditPart.VISUAL_ID:
				return new ModelEditPart(view);

			case ED2EditPart.VISUAL_ID:
				return new ED2EditPart(view);

			case ED2NameEditPart.VISUAL_ID:
				return new ED2NameEditPart(view);

			case NodeEditPart.VISUAL_ID:
				return new NodeEditPart(view);

			case NodeNameEditPart.VISUAL_ID:
				return new NodeNameEditPart(view);

			case LeafEditPart.VISUAL_ID:
				return new LeafEditPart(view);

			case LeafNameEditPart.VISUAL_ID:
				return new LeafNameEditPart(view);

			case ED2TreeElementsEditPart.VISUAL_ID:
				return new ED2TreeElementsEditPart(view);

			case NodeNodesEditPart.VISUAL_ID:
				return new NodeNodesEditPart(view);

			case NodeLeavesEditPart.VISUAL_ID:
				return new NodeLeavesEditPart(view);

			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(
			ITextAwareEditPart source) {
		return CellEditorLocatorAccess.INSTANCE
				.getTextCellEditorLocator(source);
	}

}

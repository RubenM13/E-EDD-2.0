package es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.directedit.locator.CellEditorLocatorAccess;

import es.ucm.fdi.edd.emf.model.edd.diagram.part.EddVisualIDRegistry;

/**
 * @generated
 */
public class EddEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (EddVisualIDRegistry.getVisualID(view)) {

			case EDDEditPart.VISUAL_ID:
				return new EDDEditPart(view);

			case TreeElementEditPart.VISUAL_ID:
				return new TreeElementEditPart(view);

			case TreeElementNameEditPart.VISUAL_ID:
				return new TreeElementNameEditPart(view);

			case NodeEditPart.VISUAL_ID:
				return new NodeEditPart(view);

			case NodeNameEditPart.VISUAL_ID:
				return new NodeNameEditPart(view);

			case LeafEditPart.VISUAL_ID:
				return new LeafEditPart(view);

			case LeafNameEditPart.VISUAL_ID:
				return new LeafNameEditPart(view);

			case NodeChildrenEditPart.VISUAL_ID:
				return new NodeChildrenEditPart(view);

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

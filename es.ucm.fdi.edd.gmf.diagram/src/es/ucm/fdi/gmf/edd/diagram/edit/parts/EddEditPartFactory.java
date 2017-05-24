package es.ucm.fdi.gmf.edd.diagram.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.directedit.locator.CellEditorLocatorAccess;

import es.ucm.fdi.gmf.edd.diagram.part.EddVisualIDRegistry;

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

			case DiagramEditPart.VISUAL_ID:
				return new DiagramEditPart(view);

			case ModelEditPart.VISUAL_ID:
				return new ModelEditPart(view);

			case ModelNameEditPart.VISUAL_ID:
				return new ModelNameEditPart(view);

			case BlockEditPart.VISUAL_ID:
				return new BlockEditPart(view);

			case BlockNameEditPart.VISUAL_ID:
				return new BlockNameEditPart(view);

			case TreeElementEditPart.VISUAL_ID:
				return new TreeElementEditPart(view);

			case TreeElementNameEditPart.VISUAL_ID:
				return new TreeElementNameEditPart(view);

			case ModelModelBlocksCompartmentEditPart.VISUAL_ID:
				return new ModelModelBlocksCompartmentEditPart(view);

			case BlockBlockTreeElementsCompartmentEditPart.VISUAL_ID:
				return new BlockBlockTreeElementsCompartmentEditPart(view);

			case TreeElementLinksEditPart.VISUAL_ID:
				return new TreeElementLinksEditPart(view);

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

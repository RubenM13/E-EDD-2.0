package es.ucm.fdi.gmf.edd.diagram.providers;

import org.eclipse.gmf.tooling.runtime.providers.DefaultEditPartProvider;

import es.ucm.fdi.gmf.edd.diagram.edit.parts.DiagramEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.EddEditPartFactory;
import es.ucm.fdi.gmf.edd.diagram.part.EddVisualIDRegistry;

/**
 * @generated
 */
public class EddEditPartProvider extends DefaultEditPartProvider {

	/**
	 * @generated
	 */
	public EddEditPartProvider() {
		super(new EddEditPartFactory(), EddVisualIDRegistry.TYPED_INSTANCE,
				DiagramEditPart.MODEL_ID);
	}

}

package es.ucm.fdi.emf.model.ed2.diagram.providers;

import org.eclipse.gmf.tooling.runtime.providers.DefaultEditPartProvider;

import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.Ed2EditPartFactory;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.ModelEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.part.Ed2VisualIDRegistry;

/**
 * @generated
 */
public class Ed2EditPartProvider extends DefaultEditPartProvider {

	/**
	 * @generated
	 */
	public Ed2EditPartProvider() {
		super(new Ed2EditPartFactory(), Ed2VisualIDRegistry.TYPED_INSTANCE,
				ModelEditPart.MODEL_ID);
	}

}

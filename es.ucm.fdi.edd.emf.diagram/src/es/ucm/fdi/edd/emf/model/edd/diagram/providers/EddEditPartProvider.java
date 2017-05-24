package es.ucm.fdi.edd.emf.model.edd.diagram.providers;

import org.eclipse.gmf.tooling.runtime.providers.DefaultEditPartProvider;

import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.EDDEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.EddEditPartFactory;
import es.ucm.fdi.edd.emf.model.edd.diagram.part.EddVisualIDRegistry;

/**
 * @generated
 */
public class EddEditPartProvider extends DefaultEditPartProvider {

	/**
	 * @generated
	 */
	public EddEditPartProvider() {
		super(new EddEditPartFactory(), EddVisualIDRegistry.TYPED_INSTANCE,
				EDDEditPart.MODEL_ID);
	}

}

package es.ucm.fdi.edd.emf.model.edd.diagram.providers;

import es.ucm.fdi.edd.emf.model.edd.diagram.part.EddDiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers {

	protected ElementInitializers() {
		// use #getInstance to access cached instance
	}

	/**
	 * @generated
	 */
	public static ElementInitializers getInstance() {
		ElementInitializers cached = EddDiagramEditorPlugin.getInstance()
				.getElementInitializers();
		if (cached == null) {
			EddDiagramEditorPlugin.getInstance().setElementInitializers(
					cached = new ElementInitializers());
		}
		return cached;
	}
}

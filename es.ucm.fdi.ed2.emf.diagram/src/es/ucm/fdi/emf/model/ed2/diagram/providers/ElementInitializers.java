package es.ucm.fdi.emf.model.ed2.diagram.providers;

import es.ucm.fdi.emf.model.ed2.diagram.part.Ed2DiagramEditorPlugin;

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
		ElementInitializers cached = Ed2DiagramEditorPlugin.getInstance()
				.getElementInitializers();
		if (cached == null) {
			Ed2DiagramEditorPlugin.getInstance().setElementInitializers(
					cached = new ElementInitializers());
		}
		return cached;
	}
}

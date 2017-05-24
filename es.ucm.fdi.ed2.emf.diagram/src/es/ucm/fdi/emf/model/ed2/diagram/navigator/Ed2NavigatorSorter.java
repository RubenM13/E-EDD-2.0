package es.ucm.fdi.emf.model.ed2.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import es.ucm.fdi.emf.model.ed2.diagram.part.Ed2VisualIDRegistry;

/**
 * @generated
 */
public class Ed2NavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 4005;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof Ed2NavigatorItem) {
			Ed2NavigatorItem item = (Ed2NavigatorItem) element;
			return Ed2VisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}

package es.ucm.fdi.gmf.edd.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import es.ucm.fdi.gmf.edd.diagram.part.EddVisualIDRegistry;

/**
 * @generated
 */
public class EddNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 7004;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof EddNavigatorItem) {
			EddNavigatorItem item = (EddNavigatorItem) element;
			return EddVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}

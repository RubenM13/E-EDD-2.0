package es.ucm.fdi.gmf.edd.diagram.navigator;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

import es.ucm.fdi.gmf.edd.diagram.edit.parts.BlockEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.BlockNameEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.DiagramEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.ModelEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.ModelNameEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.TreeElementEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.TreeElementLinksEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.TreeElementNameEditPart;
import es.ucm.fdi.gmf.edd.diagram.part.EddDiagramEditorPlugin;
import es.ucm.fdi.gmf.edd.diagram.part.EddVisualIDRegistry;
import es.ucm.fdi.gmf.edd.diagram.providers.EddElementTypes;
import es.ucm.fdi.gmf.edd.diagram.providers.EddParserProvider;

/**
 * @generated
 */
public class EddNavigatorLabelProvider extends LabelProvider implements
		ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		EddDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		EddDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof EddNavigatorItem
				&& !isOwnView(((EddNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof EddNavigatorGroup) {
			EddNavigatorGroup group = (EddNavigatorGroup) element;
			return EddDiagramEditorPlugin.getInstance().getBundledImage(
					group.getIcon());
		}

		if (element instanceof EddNavigatorItem) {
			EddNavigatorItem navigatorItem = (EddNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	 * @generated
	 */
	public Image getImage(View view) {
		switch (EddVisualIDRegistry.getVisualID(view)) {
		case DiagramEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://es/ucm/fdi/gmf/1.0?Diagram", EddElementTypes.Diagram_1000); //$NON-NLS-1$
		case ModelEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://es/ucm/fdi/gmf/1.0?Model", EddElementTypes.Model_2001); //$NON-NLS-1$
		case BlockEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://es/ucm/fdi/gmf/1.0?Block", EddElementTypes.Block_3001); //$NON-NLS-1$
		case TreeElementEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://es/ucm/fdi/gmf/1.0?TreeElement", EddElementTypes.TreeElement_3002); //$NON-NLS-1$
		case TreeElementLinksEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://es/ucm/fdi/gmf/1.0?TreeElement?links", EddElementTypes.TreeElementLinks_4001); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = EddDiagramEditorPlugin.getInstance()
				.getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& EddElementTypes.isKnownElementType(elementType)) {
			image = EddElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public String getText(Object element) {
		if (element instanceof EddNavigatorGroup) {
			EddNavigatorGroup group = (EddNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof EddNavigatorItem) {
			EddNavigatorItem navigatorItem = (EddNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		return super.getText(element);
	}

	/**
	 * @generated
	 */
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}
		switch (EddVisualIDRegistry.getVisualID(view)) {
		case DiagramEditPart.VISUAL_ID:
			return getDiagram_1000Text(view);
		case ModelEditPart.VISUAL_ID:
			return getModel_2001Text(view);
		case BlockEditPart.VISUAL_ID:
			return getBlock_3001Text(view);
		case TreeElementEditPart.VISUAL_ID:
			return getTreeElement_3002Text(view);
		case TreeElementLinksEditPart.VISUAL_ID:
			return getTreeElementLinks_4001Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getDiagram_1000Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getModel_2001Text(View view) {
		IParser parser = EddParserProvider.getParser(
				EddElementTypes.Model_2001,
				view.getElement() != null ? view.getElement() : view,
				EddVisualIDRegistry.getType(ModelNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			EddDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getBlock_3001Text(View view) {
		IParser parser = EddParserProvider.getParser(
				EddElementTypes.Block_3001,
				view.getElement() != null ? view.getElement() : view,
				EddVisualIDRegistry.getType(BlockNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			EddDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getTreeElement_3002Text(View view) {
		IParser parser = EddParserProvider.getParser(
				EddElementTypes.TreeElement_3002,
				view.getElement() != null ? view.getElement() : view,
				EddVisualIDRegistry.getType(TreeElementNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			EddDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getTreeElementLinks_4001Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return DiagramEditPart.MODEL_ID.equals(EddVisualIDRegistry
				.getModelID(view));
	}

}

package es.ucm.fdi.edd.emf.model.edd.diagram.navigator;

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

import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.EDDEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.LeafEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.LeafNameEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.NodeChildrenEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.NodeEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.NodeNameEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.TreeElementEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.TreeElementNameEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.part.EddDiagramEditorPlugin;
import es.ucm.fdi.edd.emf.model.edd.diagram.part.EddVisualIDRegistry;
import es.ucm.fdi.edd.emf.model.edd.diagram.providers.EddElementTypes;
import es.ucm.fdi.edd.emf.model.edd.diagram.providers.EddParserProvider;

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
		case EDDEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://edd/1.0?EDD", EddElementTypes.EDD_1000); //$NON-NLS-1$
		case NodeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://edd/1.0?Node", EddElementTypes.Node_2001); //$NON-NLS-1$
		case LeafEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://edd/1.0?Leaf", EddElementTypes.Leaf_2002); //$NON-NLS-1$
		case TreeElementEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://edd/1.0?TreeElement", EddElementTypes.TreeElement_2003); //$NON-NLS-1$
		case NodeChildrenEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://edd/1.0?Node?children", EddElementTypes.NodeChildren_4001); //$NON-NLS-1$
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
		case EDDEditPart.VISUAL_ID:
			return getEDD_1000Text(view);
		case NodeEditPart.VISUAL_ID:
			return getNode_2001Text(view);
		case LeafEditPart.VISUAL_ID:
			return getLeaf_2002Text(view);
		case TreeElementEditPart.VISUAL_ID:
			return getTreeElement_2003Text(view);
		case NodeChildrenEditPart.VISUAL_ID:
			return getNodeChildren_4001Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getEDD_1000Text(View view) {
		es.ucm.fdi.edd.emf.model.edd.EDD domainModelElement = (es.ucm.fdi.edd.emf.model.edd.EDD) view
				.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			EddDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 1000); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getNode_2001Text(View view) {
		IParser parser = EddParserProvider.getParser(EddElementTypes.Node_2001,
				view.getElement() != null ? view.getElement() : view,
				EddVisualIDRegistry.getType(NodeNameEditPart.VISUAL_ID));
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
	private String getLeaf_2002Text(View view) {
		IParser parser = EddParserProvider.getParser(EddElementTypes.Leaf_2002,
				view.getElement() != null ? view.getElement() : view,
				EddVisualIDRegistry.getType(LeafNameEditPart.VISUAL_ID));
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
	private String getTreeElement_2003Text(View view) {
		IParser parser = EddParserProvider.getParser(
				EddElementTypes.TreeElement_2003,
				view.getElement() != null ? view.getElement() : view,
				EddVisualIDRegistry.getType(TreeElementNameEditPart.VISUAL_ID));
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
	private String getNodeChildren_4001Text(View view) {
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
		return EDDEditPart.MODEL_ID
				.equals(EddVisualIDRegistry.getModelID(view));
	}

}

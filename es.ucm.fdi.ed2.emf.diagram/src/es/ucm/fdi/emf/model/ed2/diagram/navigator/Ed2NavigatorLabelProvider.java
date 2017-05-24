package es.ucm.fdi.emf.model.ed2.diagram.navigator;

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

import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.ED2EditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.ED2NameEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.ED2TreeElementsEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.LeafEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.LeafNameEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.ModelEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.NodeEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.NodeLeavesEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.NodeNameEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.NodeNodesEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.part.Ed2DiagramEditorPlugin;
import es.ucm.fdi.emf.model.ed2.diagram.part.Ed2VisualIDRegistry;
import es.ucm.fdi.emf.model.ed2.diagram.providers.Ed2ElementTypes;
import es.ucm.fdi.emf.model.ed2.diagram.providers.Ed2ParserProvider;

/**
 * @generated
 */
public class Ed2NavigatorLabelProvider extends LabelProvider implements
		ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		Ed2DiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		Ed2DiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof Ed2NavigatorItem
				&& !isOwnView(((Ed2NavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof Ed2NavigatorGroup) {
			Ed2NavigatorGroup group = (Ed2NavigatorGroup) element;
			return Ed2DiagramEditorPlugin.getInstance().getBundledImage(
					group.getIcon());
		}

		if (element instanceof Ed2NavigatorItem) {
			Ed2NavigatorItem navigatorItem = (Ed2NavigatorItem) element;
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
		switch (Ed2VisualIDRegistry.getVisualID(view)) {
		case ModelEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://ed2/1.0?Model", Ed2ElementTypes.Model_1000); //$NON-NLS-1$
		case NodeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://ed2/1.0?Node", Ed2ElementTypes.Node_2006); //$NON-NLS-1$
		case LeafEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://ed2/1.0?Leaf", Ed2ElementTypes.Leaf_2007); //$NON-NLS-1$
		case ED2EditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://ed2/1.0?ED2", Ed2ElementTypes.ED2_2008); //$NON-NLS-1$
		case ED2TreeElementsEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://ed2/1.0?ED2?treeElements", Ed2ElementTypes.ED2TreeElements_4001); //$NON-NLS-1$
		case NodeNodesEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://ed2/1.0?Node?nodes", Ed2ElementTypes.NodeNodes_4002); //$NON-NLS-1$
		case NodeLeavesEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://ed2/1.0?Node?leaves", Ed2ElementTypes.NodeLeaves_4003); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = Ed2DiagramEditorPlugin.getInstance()
				.getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& Ed2ElementTypes.isKnownElementType(elementType)) {
			image = Ed2ElementTypes.getImage(elementType);
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
		if (element instanceof Ed2NavigatorGroup) {
			Ed2NavigatorGroup group = (Ed2NavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof Ed2NavigatorItem) {
			Ed2NavigatorItem navigatorItem = (Ed2NavigatorItem) element;
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
		switch (Ed2VisualIDRegistry.getVisualID(view)) {
		case ModelEditPart.VISUAL_ID:
			return getModel_1000Text(view);
		case NodeEditPart.VISUAL_ID:
			return getNode_2006Text(view);
		case LeafEditPart.VISUAL_ID:
			return getLeaf_2007Text(view);
		case ED2EditPart.VISUAL_ID:
			return getED2_2008Text(view);
		case ED2TreeElementsEditPart.VISUAL_ID:
			return getED2TreeElements_4001Text(view);
		case NodeNodesEditPart.VISUAL_ID:
			return getNodeNodes_4002Text(view);
		case NodeLeavesEditPart.VISUAL_ID:
			return getNodeLeaves_4003Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getModel_1000Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getNode_2006Text(View view) {
		IParser parser = Ed2ParserProvider.getParser(Ed2ElementTypes.Node_2006,
				view.getElement() != null ? view.getElement() : view,
				Ed2VisualIDRegistry.getType(NodeNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			Ed2DiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5008); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getLeaf_2007Text(View view) {
		IParser parser = Ed2ParserProvider.getParser(Ed2ElementTypes.Leaf_2007,
				view.getElement() != null ? view.getElement() : view,
				Ed2VisualIDRegistry.getType(LeafNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			Ed2DiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5009); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getED2_2008Text(View view) {
		IParser parser = Ed2ParserProvider.getParser(Ed2ElementTypes.ED2_2008,
				view.getElement() != null ? view.getElement() : view,
				Ed2VisualIDRegistry.getType(ED2NameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			Ed2DiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5010); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getED2TreeElements_4001Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getNodeNodes_4002Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getNodeLeaves_4003Text(View view) {
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
		return ModelEditPart.MODEL_ID.equals(Ed2VisualIDRegistry
				.getModelID(view));
	}

}

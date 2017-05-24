package es.ucm.fdi.edd.emf.model.edd.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.update.DiagramUpdater;

import es.ucm.fdi.edd.emf.model.edd.EddPackage;
import es.ucm.fdi.edd.emf.model.edd.Leaf;
import es.ucm.fdi.edd.emf.model.edd.Node;
import es.ucm.fdi.edd.emf.model.edd.TreeElement;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.EDDEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.LeafEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.NodeChildrenEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.NodeEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.TreeElementEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.providers.EddElementTypes;

/**
 * @generated
 */
public class EddDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<EddNodeDescriptor> getSemanticChildren(View view) {
		switch (EddVisualIDRegistry.getVisualID(view)) {
		case EDDEditPart.VISUAL_ID:
			return getEDD_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EddNodeDescriptor> getEDD_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		es.ucm.fdi.edd.emf.model.edd.EDD modelElement = (es.ucm.fdi.edd.emf.model.edd.EDD) view
				.getElement();
		LinkedList<EddNodeDescriptor> result = new LinkedList<EddNodeDescriptor>();
		for (Iterator<?> it = modelElement.getElements().iterator(); it
				.hasNext();) {
			TreeElement childElement = (TreeElement) it.next();
			int visualID = EddVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == TreeElementEditPart.VISUAL_ID) {
				result.add(new EddNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == NodeEditPart.VISUAL_ID) {
				result.add(new EddNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == LeafEditPart.VISUAL_ID) {
				result.add(new EddNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getContainedLinks(View view) {
		switch (EddVisualIDRegistry.getVisualID(view)) {
		case EDDEditPart.VISUAL_ID:
			return getEDD_1000ContainedLinks(view);
		case TreeElementEditPart.VISUAL_ID:
			return getTreeElement_2003ContainedLinks(view);
		case NodeEditPart.VISUAL_ID:
			return getNode_2001ContainedLinks(view);
		case LeafEditPart.VISUAL_ID:
			return getLeaf_2002ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getIncomingLinks(View view) {
		switch (EddVisualIDRegistry.getVisualID(view)) {
		case TreeElementEditPart.VISUAL_ID:
			return getTreeElement_2003IncomingLinks(view);
		case NodeEditPart.VISUAL_ID:
			return getNode_2001IncomingLinks(view);
		case LeafEditPart.VISUAL_ID:
			return getLeaf_2002IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getOutgoingLinks(View view) {
		switch (EddVisualIDRegistry.getVisualID(view)) {
		case TreeElementEditPart.VISUAL_ID:
			return getTreeElement_2003OutgoingLinks(view);
		case NodeEditPart.VISUAL_ID:
			return getNode_2001OutgoingLinks(view);
		case LeafEditPart.VISUAL_ID:
			return getLeaf_2002OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getEDD_1000ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getTreeElement_2003ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getNode_2001ContainedLinks(View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<EddLinkDescriptor> result = new LinkedList<EddLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Node_Children_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getLeaf_2002ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getTreeElement_2003IncomingLinks(
			View view) {
		TreeElement modelElement = (TreeElement) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<EddLinkDescriptor> result = new LinkedList<EddLinkDescriptor>();
		result.addAll(getIncomingFeatureModelFacetLinks_Node_Children_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getNode_2001IncomingLinks(View view) {
		Node modelElement = (Node) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<EddLinkDescriptor> result = new LinkedList<EddLinkDescriptor>();
		result.addAll(getIncomingFeatureModelFacetLinks_Node_Children_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getLeaf_2002IncomingLinks(View view) {
		Leaf modelElement = (Leaf) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<EddLinkDescriptor> result = new LinkedList<EddLinkDescriptor>();
		result.addAll(getIncomingFeatureModelFacetLinks_Node_Children_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getTreeElement_2003OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getNode_2001OutgoingLinks(View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<EddLinkDescriptor> result = new LinkedList<EddLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Node_Children_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getLeaf_2002OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<EddLinkDescriptor> getIncomingFeatureModelFacetLinks_Node_Children_4001(
			TreeElement target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<EddLinkDescriptor> result = new LinkedList<EddLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == EddPackage.eINSTANCE
					.getNode_Children()) {
				result.add(new EddLinkDescriptor(setting.getEObject(), target,
						EddElementTypes.NodeChildren_4001,
						NodeChildrenEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<EddLinkDescriptor> getOutgoingFeatureModelFacetLinks_Node_Children_4001(
			Node source) {
		LinkedList<EddLinkDescriptor> result = new LinkedList<EddLinkDescriptor>();
		for (Iterator<?> destinations = source.getChildren().iterator(); destinations
				.hasNext();) {
			TreeElement destination = (TreeElement) destinations.next();
			result.add(new EddLinkDescriptor(source, destination,
					EddElementTypes.NodeChildren_4001,
					NodeChildrenEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static final DiagramUpdater TYPED_INSTANCE = new DiagramUpdater() {
		/**
		 * @generated
		 */
		@Override
		public List<EddNodeDescriptor> getSemanticChildren(View view) {
			return EddDiagramUpdater.getSemanticChildren(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<EddLinkDescriptor> getContainedLinks(View view) {
			return EddDiagramUpdater.getContainedLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<EddLinkDescriptor> getIncomingLinks(View view) {
			return EddDiagramUpdater.getIncomingLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<EddLinkDescriptor> getOutgoingLinks(View view) {
			return EddDiagramUpdater.getOutgoingLinks(view);
		}
	};

}

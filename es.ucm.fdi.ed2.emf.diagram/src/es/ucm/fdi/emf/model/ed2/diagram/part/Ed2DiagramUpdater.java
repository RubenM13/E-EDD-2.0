package es.ucm.fdi.emf.model.ed2.diagram.part;

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

import es.ucm.fdi.emf.model.ed2.Ed2Package;
import es.ucm.fdi.emf.model.ed2.Leaf;
import es.ucm.fdi.emf.model.ed2.Model;
import es.ucm.fdi.emf.model.ed2.Node;
import es.ucm.fdi.emf.model.ed2.TreeElement;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.ED2EditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.ED2TreeElementsEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.LeafEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.ModelEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.NodeEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.NodeLeavesEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.NodeNodesEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.providers.Ed2ElementTypes;

/**
 * @generated
 */
public class Ed2DiagramUpdater {

	/**
	 * @generated
	 */
	public static List<Ed2NodeDescriptor> getSemanticChildren(View view) {
		switch (Ed2VisualIDRegistry.getVisualID(view)) {
		case ModelEditPart.VISUAL_ID:
			return getModel_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<Ed2NodeDescriptor> getModel_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Model modelElement = (Model) view.getElement();
		LinkedList<Ed2NodeDescriptor> result = new LinkedList<Ed2NodeDescriptor>();
		{
			es.ucm.fdi.emf.model.ed2.ED2 childElement = modelElement.getEd2();
			int visualID = Ed2VisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ED2EditPart.VISUAL_ID) {
				result.add(new Ed2NodeDescriptor(childElement, visualID));
			}
		}
		Resource resource = modelElement.eResource();
		for (Iterator<EObject> it = getPhantomNodesIterator(resource); it
				.hasNext();) {
			EObject childElement = it.next();
			if (childElement == modelElement) {
				continue;
			}
			if (Ed2VisualIDRegistry.getNodeVisualID(view, childElement) == NodeEditPart.VISUAL_ID) {
				result.add(new Ed2NodeDescriptor(childElement,
						NodeEditPart.VISUAL_ID));
				continue;
			}
			if (Ed2VisualIDRegistry.getNodeVisualID(view, childElement) == LeafEditPart.VISUAL_ID) {
				result.add(new Ed2NodeDescriptor(childElement,
						LeafEditPart.VISUAL_ID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Iterator<EObject> getPhantomNodesIterator(Resource resource) {
		return resource.getAllContents();
	}

	/**
	 * @generated
	 */
	public static List<Ed2LinkDescriptor> getContainedLinks(View view) {
		switch (Ed2VisualIDRegistry.getVisualID(view)) {
		case ModelEditPart.VISUAL_ID:
			return getModel_1000ContainedLinks(view);
		case ED2EditPart.VISUAL_ID:
			return getED2_2008ContainedLinks(view);
		case NodeEditPart.VISUAL_ID:
			return getNode_2006ContainedLinks(view);
		case LeafEditPart.VISUAL_ID:
			return getLeaf_2007ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<Ed2LinkDescriptor> getIncomingLinks(View view) {
		switch (Ed2VisualIDRegistry.getVisualID(view)) {
		case ED2EditPart.VISUAL_ID:
			return getED2_2008IncomingLinks(view);
		case NodeEditPart.VISUAL_ID:
			return getNode_2006IncomingLinks(view);
		case LeafEditPart.VISUAL_ID:
			return getLeaf_2007IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<Ed2LinkDescriptor> getOutgoingLinks(View view) {
		switch (Ed2VisualIDRegistry.getVisualID(view)) {
		case ED2EditPart.VISUAL_ID:
			return getED2_2008OutgoingLinks(view);
		case NodeEditPart.VISUAL_ID:
			return getNode_2006OutgoingLinks(view);
		case LeafEditPart.VISUAL_ID:
			return getLeaf_2007OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<Ed2LinkDescriptor> getModel_1000ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<Ed2LinkDescriptor> getED2_2008ContainedLinks(View view) {
		es.ucm.fdi.emf.model.ed2.ED2 modelElement = (es.ucm.fdi.emf.model.ed2.ED2) view
				.getElement();
		LinkedList<Ed2LinkDescriptor> result = new LinkedList<Ed2LinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_ED2_TreeElements_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<Ed2LinkDescriptor> getNode_2006ContainedLinks(View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<Ed2LinkDescriptor> result = new LinkedList<Ed2LinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Node_Nodes_4002(modelElement));
		result.addAll(getOutgoingFeatureModelFacetLinks_Node_Leaves_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<Ed2LinkDescriptor> getLeaf_2007ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<Ed2LinkDescriptor> getED2_2008IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<Ed2LinkDescriptor> getNode_2006IncomingLinks(View view) {
		Node modelElement = (Node) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<Ed2LinkDescriptor> result = new LinkedList<Ed2LinkDescriptor>();
		result.addAll(getIncomingFeatureModelFacetLinks_ED2_TreeElements_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_Node_Nodes_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<Ed2LinkDescriptor> getLeaf_2007IncomingLinks(View view) {
		Leaf modelElement = (Leaf) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<Ed2LinkDescriptor> result = new LinkedList<Ed2LinkDescriptor>();
		result.addAll(getIncomingFeatureModelFacetLinks_ED2_TreeElements_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_Node_Leaves_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<Ed2LinkDescriptor> getED2_2008OutgoingLinks(View view) {
		es.ucm.fdi.emf.model.ed2.ED2 modelElement = (es.ucm.fdi.emf.model.ed2.ED2) view
				.getElement();
		LinkedList<Ed2LinkDescriptor> result = new LinkedList<Ed2LinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_ED2_TreeElements_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<Ed2LinkDescriptor> getNode_2006OutgoingLinks(View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<Ed2LinkDescriptor> result = new LinkedList<Ed2LinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Node_Nodes_4002(modelElement));
		result.addAll(getOutgoingFeatureModelFacetLinks_Node_Leaves_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<Ed2LinkDescriptor> getLeaf_2007OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<Ed2LinkDescriptor> getIncomingFeatureModelFacetLinks_ED2_TreeElements_4001(
			TreeElement target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<Ed2LinkDescriptor> result = new LinkedList<Ed2LinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == Ed2Package.eINSTANCE
					.getED2_TreeElements()) {
				result.add(new Ed2LinkDescriptor(setting.getEObject(), target,
						Ed2ElementTypes.ED2TreeElements_4001,
						ED2TreeElementsEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<Ed2LinkDescriptor> getIncomingFeatureModelFacetLinks_Node_Nodes_4002(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<Ed2LinkDescriptor> result = new LinkedList<Ed2LinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == Ed2Package.eINSTANCE
					.getNode_Nodes()) {
				result.add(new Ed2LinkDescriptor(setting.getEObject(), target,
						Ed2ElementTypes.NodeNodes_4002,
						NodeNodesEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<Ed2LinkDescriptor> getIncomingFeatureModelFacetLinks_Node_Leaves_4003(
			Leaf target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<Ed2LinkDescriptor> result = new LinkedList<Ed2LinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == Ed2Package.eINSTANCE
					.getNode_Leaves()) {
				result.add(new Ed2LinkDescriptor(setting.getEObject(), target,
						Ed2ElementTypes.NodeLeaves_4003,
						NodeLeavesEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<Ed2LinkDescriptor> getOutgoingFeatureModelFacetLinks_ED2_TreeElements_4001(
			es.ucm.fdi.emf.model.ed2.ED2 source) {
		LinkedList<Ed2LinkDescriptor> result = new LinkedList<Ed2LinkDescriptor>();
		for (Iterator<?> destinations = source.getTreeElements().iterator(); destinations
				.hasNext();) {
			TreeElement destination = (TreeElement) destinations.next();
			result.add(new Ed2LinkDescriptor(source, destination,
					Ed2ElementTypes.ED2TreeElements_4001,
					ED2TreeElementsEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<Ed2LinkDescriptor> getOutgoingFeatureModelFacetLinks_Node_Nodes_4002(
			Node source) {
		LinkedList<Ed2LinkDescriptor> result = new LinkedList<Ed2LinkDescriptor>();
		for (Iterator<?> destinations = source.getNodes().iterator(); destinations
				.hasNext();) {
			Node destination = (Node) destinations.next();
			result.add(new Ed2LinkDescriptor(source, destination,
					Ed2ElementTypes.NodeNodes_4002, NodeNodesEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<Ed2LinkDescriptor> getOutgoingFeatureModelFacetLinks_Node_Leaves_4003(
			Node source) {
		LinkedList<Ed2LinkDescriptor> result = new LinkedList<Ed2LinkDescriptor>();
		for (Iterator<?> destinations = source.getLeaves().iterator(); destinations
				.hasNext();) {
			Leaf destination = (Leaf) destinations.next();
			result.add(new Ed2LinkDescriptor(source, destination,
					Ed2ElementTypes.NodeLeaves_4003,
					NodeLeavesEditPart.VISUAL_ID));
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
		public List<Ed2NodeDescriptor> getSemanticChildren(View view) {
			return Ed2DiagramUpdater.getSemanticChildren(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<Ed2LinkDescriptor> getContainedLinks(View view) {
			return Ed2DiagramUpdater.getContainedLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<Ed2LinkDescriptor> getIncomingLinks(View view) {
			return Ed2DiagramUpdater.getIncomingLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<Ed2LinkDescriptor> getOutgoingLinks(View view) {
			return Ed2DiagramUpdater.getOutgoingLinks(view);
		}
	};

}

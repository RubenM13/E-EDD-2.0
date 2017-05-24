package es.ucm.fdi.gmf.edd.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.update.DiagramUpdater;

import es.ucm.fdi.gmf.edd.Block;
import es.ucm.fdi.gmf.edd.Diagram;
import es.ucm.fdi.gmf.edd.EddPackage;
import es.ucm.fdi.gmf.edd.Model;
import es.ucm.fdi.gmf.edd.TreeElement;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.BlockBlockTreeElementsCompartmentEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.BlockEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.DiagramEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.ModelEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.ModelModelBlocksCompartmentEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.TreeElementEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.TreeElementLinksEditPart;
import es.ucm.fdi.gmf.edd.diagram.providers.EddElementTypes;

/**
 * @generated
 */
public class EddDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<EddNodeDescriptor> getSemanticChildren(View view) {
		switch (EddVisualIDRegistry.getVisualID(view)) {
		case DiagramEditPart.VISUAL_ID:
			return getDiagram_1000SemanticChildren(view);
		case ModelModelBlocksCompartmentEditPart.VISUAL_ID:
			return getModelModelBlocksCompartment_7001SemanticChildren(view);
		case BlockBlockTreeElementsCompartmentEditPart.VISUAL_ID:
			return getBlockBlockTreeElementsCompartment_7002SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EddNodeDescriptor> getDiagram_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Diagram modelElement = (Diagram) view.getElement();
		LinkedList<EddNodeDescriptor> result = new LinkedList<EddNodeDescriptor>();
		{
			Model childElement = modelElement.getModel();
			int visualID = EddVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ModelEditPart.VISUAL_ID) {
				result.add(new EddNodeDescriptor(childElement, visualID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EddNodeDescriptor> getModelModelBlocksCompartment_7001SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Model modelElement = (Model) containerView.getElement();
		LinkedList<EddNodeDescriptor> result = new LinkedList<EddNodeDescriptor>();
		for (Iterator<?> it = modelElement.getBlocks().iterator(); it.hasNext();) {
			Block childElement = (Block) it.next();
			int visualID = EddVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == BlockEditPart.VISUAL_ID) {
				result.add(new EddNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EddNodeDescriptor> getBlockBlockTreeElementsCompartment_7002SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Block modelElement = (Block) containerView.getElement();
		LinkedList<EddNodeDescriptor> result = new LinkedList<EddNodeDescriptor>();
		for (Iterator<?> it = modelElement.getItems().iterator(); it.hasNext();) {
			TreeElement childElement = (TreeElement) it.next();
			int visualID = EddVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == TreeElementEditPart.VISUAL_ID) {
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
		case DiagramEditPart.VISUAL_ID:
			return getDiagram_1000ContainedLinks(view);
		case ModelEditPart.VISUAL_ID:
			return getModel_2001ContainedLinks(view);
		case BlockEditPart.VISUAL_ID:
			return getBlock_3001ContainedLinks(view);
		case TreeElementEditPart.VISUAL_ID:
			return getTreeElement_3002ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getIncomingLinks(View view) {
		switch (EddVisualIDRegistry.getVisualID(view)) {
		case ModelEditPart.VISUAL_ID:
			return getModel_2001IncomingLinks(view);
		case BlockEditPart.VISUAL_ID:
			return getBlock_3001IncomingLinks(view);
		case TreeElementEditPart.VISUAL_ID:
			return getTreeElement_3002IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getOutgoingLinks(View view) {
		switch (EddVisualIDRegistry.getVisualID(view)) {
		case ModelEditPart.VISUAL_ID:
			return getModel_2001OutgoingLinks(view);
		case BlockEditPart.VISUAL_ID:
			return getBlock_3001OutgoingLinks(view);
		case TreeElementEditPart.VISUAL_ID:
			return getTreeElement_3002OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getDiagram_1000ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getModel_2001ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getBlock_3001ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getTreeElement_3002ContainedLinks(
			View view) {
		TreeElement modelElement = (TreeElement) view.getElement();
		LinkedList<EddLinkDescriptor> result = new LinkedList<EddLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_TreeElement_Links_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getModel_2001IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getBlock_3001IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getTreeElement_3002IncomingLinks(
			View view) {
		TreeElement modelElement = (TreeElement) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<EddLinkDescriptor> result = new LinkedList<EddLinkDescriptor>();
		result.addAll(getIncomingFeatureModelFacetLinks_TreeElement_Links_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getModel_2001OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getBlock_3001OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<EddLinkDescriptor> getTreeElement_3002OutgoingLinks(
			View view) {
		TreeElement modelElement = (TreeElement) view.getElement();
		LinkedList<EddLinkDescriptor> result = new LinkedList<EddLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_TreeElement_Links_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<EddLinkDescriptor> getIncomingFeatureModelFacetLinks_TreeElement_Links_4001(
			TreeElement target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<EddLinkDescriptor> result = new LinkedList<EddLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == EddPackage.eINSTANCE
					.getTreeElement_Links()) {
				result.add(new EddLinkDescriptor(setting.getEObject(), target,
						EddElementTypes.TreeElementLinks_4001,
						TreeElementLinksEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<EddLinkDescriptor> getOutgoingFeatureModelFacetLinks_TreeElement_Links_4001(
			TreeElement source) {
		LinkedList<EddLinkDescriptor> result = new LinkedList<EddLinkDescriptor>();
		for (Iterator<?> destinations = source.getLinks().iterator(); destinations
				.hasNext();) {
			TreeElement destination = (TreeElement) destinations.next();
			result.add(new EddLinkDescriptor(source, destination,
					EddElementTypes.TreeElementLinks_4001,
					TreeElementLinksEditPart.VISUAL_ID));
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

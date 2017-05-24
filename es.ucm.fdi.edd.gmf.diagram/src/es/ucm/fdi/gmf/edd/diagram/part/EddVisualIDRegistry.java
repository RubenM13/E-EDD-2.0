package es.ucm.fdi.gmf.edd.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.structure.DiagramStructure;

import es.ucm.fdi.gmf.edd.EddPackage;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.BlockBlockTreeElementsCompartmentEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.BlockEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.BlockNameEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.DiagramEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.ModelEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.ModelModelBlocksCompartmentEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.ModelNameEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.TreeElementEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.TreeElementNameEditPart;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class EddVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "es.ucm.fdi.edd.gmf.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (DiagramEditPart.MODEL_ID.equals(view.getType())) {
				return DiagramEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return es.ucm.fdi.gmf.edd.diagram.part.EddVisualIDRegistry
				.getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				EddDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "
								+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return Integer.toString(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (EddPackage.eINSTANCE.getDiagram().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((es.ucm.fdi.gmf.edd.Diagram) domainElement)) {
			return DiagramEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = es.ucm.fdi.gmf.edd.diagram.part.EddVisualIDRegistry
				.getModelID(containerView);
		if (!DiagramEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (DiagramEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = es.ucm.fdi.gmf.edd.diagram.part.EddVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = DiagramEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case DiagramEditPart.VISUAL_ID:
			if (EddPackage.eINSTANCE.getModel().isSuperTypeOf(
					domainElement.eClass())) {
				return ModelEditPart.VISUAL_ID;
			}
			break;
		case ModelModelBlocksCompartmentEditPart.VISUAL_ID:
			if (EddPackage.eINSTANCE.getBlock().isSuperTypeOf(
					domainElement.eClass())) {
				return BlockEditPart.VISUAL_ID;
			}
			break;
		case BlockBlockTreeElementsCompartmentEditPart.VISUAL_ID:
			if (EddPackage.eINSTANCE.getTreeElement().isSuperTypeOf(
					domainElement.eClass())) {
				return TreeElementEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = es.ucm.fdi.gmf.edd.diagram.part.EddVisualIDRegistry
				.getModelID(containerView);
		if (!DiagramEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (DiagramEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = es.ucm.fdi.gmf.edd.diagram.part.EddVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = DiagramEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case DiagramEditPart.VISUAL_ID:
			if (ModelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ModelEditPart.VISUAL_ID:
			if (ModelNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ModelModelBlocksCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BlockEditPart.VISUAL_ID:
			if (BlockNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BlockBlockTreeElementsCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TreeElementEditPart.VISUAL_ID:
			if (TreeElementNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ModelModelBlocksCompartmentEditPart.VISUAL_ID:
			if (BlockEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BlockBlockTreeElementsCompartmentEditPart.VISUAL_ID:
			if (TreeElementEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(es.ucm.fdi.gmf.edd.Diagram element) {
		return true;
	}

	/**
	 * @generated
	 */
	public static boolean checkNodeVisualID(View containerView,
			EObject domainElement, int candidate) {
		if (candidate == -1) {
			//unrecognized id is always bad
			return false;
		}
		int basic = getNodeVisualID(containerView, domainElement);
		return basic == candidate;
	}

	/**
	 * @generated
	 */
	public static boolean isCompartmentVisualID(int visualID) {
		switch (visualID) {
		case ModelModelBlocksCompartmentEditPart.VISUAL_ID:
		case BlockBlockTreeElementsCompartmentEditPart.VISUAL_ID:
			return true;
		default:
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static boolean isSemanticLeafVisualID(int visualID) {
		switch (visualID) {
		case DiagramEditPart.VISUAL_ID:
			return false;
		case TreeElementEditPart.VISUAL_ID:
			return true;
		default:
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static final DiagramStructure TYPED_INSTANCE = new DiagramStructure() {
		/**
		 * @generated
		 */
		@Override
		public int getVisualID(View view) {
			return es.ucm.fdi.gmf.edd.diagram.part.EddVisualIDRegistry
					.getVisualID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public String getModelID(View view) {
			return es.ucm.fdi.gmf.edd.diagram.part.EddVisualIDRegistry
					.getModelID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public int getNodeVisualID(View containerView, EObject domainElement) {
			return es.ucm.fdi.gmf.edd.diagram.part.EddVisualIDRegistry
					.getNodeVisualID(containerView, domainElement);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean checkNodeVisualID(View containerView,
				EObject domainElement, int candidate) {
			return es.ucm.fdi.gmf.edd.diagram.part.EddVisualIDRegistry
					.checkNodeVisualID(containerView, domainElement, candidate);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isCompartmentVisualID(int visualID) {
			return es.ucm.fdi.gmf.edd.diagram.part.EddVisualIDRegistry
					.isCompartmentVisualID(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isSemanticLeafVisualID(int visualID) {
			return es.ucm.fdi.gmf.edd.diagram.part.EddVisualIDRegistry
					.isSemanticLeafVisualID(visualID);
		}
	};

}

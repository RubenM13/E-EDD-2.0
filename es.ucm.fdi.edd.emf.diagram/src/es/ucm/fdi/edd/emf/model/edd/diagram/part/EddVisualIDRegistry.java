package es.ucm.fdi.edd.emf.model.edd.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.structure.DiagramStructure;

import es.ucm.fdi.edd.emf.model.edd.EddPackage;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.EDDEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.LeafEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.LeafNameEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.NodeEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.NodeNameEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.TreeElementEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.TreeElementNameEditPart;

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
	private static final String DEBUG_KEY = "es.ucm.fdi.edd.emf.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (EDDEditPart.MODEL_ID.equals(view.getType())) {
				return EDDEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return es.ucm.fdi.edd.emf.model.edd.diagram.part.EddVisualIDRegistry
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
		if (EddPackage.eINSTANCE.getEDD().isSuperTypeOf(domainElement.eClass())
				&& isDiagram((es.ucm.fdi.edd.emf.model.edd.EDD) domainElement)) {
			return EDDEditPart.VISUAL_ID;
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
		String containerModelID = es.ucm.fdi.edd.emf.model.edd.diagram.part.EddVisualIDRegistry
				.getModelID(containerView);
		if (!EDDEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (EDDEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = es.ucm.fdi.edd.emf.model.edd.diagram.part.EddVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = EDDEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case EDDEditPart.VISUAL_ID:
			if (EddPackage.eINSTANCE.getTreeElement().isSuperTypeOf(
					domainElement.eClass())) {
				return TreeElementEditPart.VISUAL_ID;
			}
			if (EddPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())) {
				return NodeEditPart.VISUAL_ID;
			}
			if (EddPackage.eINSTANCE.getLeaf().isSuperTypeOf(
					domainElement.eClass())) {
				return LeafEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = es.ucm.fdi.edd.emf.model.edd.diagram.part.EddVisualIDRegistry
				.getModelID(containerView);
		if (!EDDEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (EDDEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = es.ucm.fdi.edd.emf.model.edd.diagram.part.EddVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = EDDEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case EDDEditPart.VISUAL_ID:
			if (TreeElementEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (NodeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (LeafEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TreeElementEditPart.VISUAL_ID:
			if (TreeElementNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case NodeEditPart.VISUAL_ID:
			if (NodeNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case LeafEditPart.VISUAL_ID:
			if (LeafNameEditPart.VISUAL_ID == nodeVisualID) {
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
	private static boolean isDiagram(es.ucm.fdi.edd.emf.model.edd.EDD element) {
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
		return false;
	}

	/**
	 * @generated
	 */
	public static boolean isSemanticLeafVisualID(int visualID) {
		switch (visualID) {
		case EDDEditPart.VISUAL_ID:
			return false;
		case NodeEditPart.VISUAL_ID:
		case LeafEditPart.VISUAL_ID:
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
			return es.ucm.fdi.edd.emf.model.edd.diagram.part.EddVisualIDRegistry
					.getVisualID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public String getModelID(View view) {
			return es.ucm.fdi.edd.emf.model.edd.diagram.part.EddVisualIDRegistry
					.getModelID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public int getNodeVisualID(View containerView, EObject domainElement) {
			return es.ucm.fdi.edd.emf.model.edd.diagram.part.EddVisualIDRegistry
					.getNodeVisualID(containerView, domainElement);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean checkNodeVisualID(View containerView,
				EObject domainElement, int candidate) {
			return es.ucm.fdi.edd.emf.model.edd.diagram.part.EddVisualIDRegistry
					.checkNodeVisualID(containerView, domainElement, candidate);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isCompartmentVisualID(int visualID) {
			return es.ucm.fdi.edd.emf.model.edd.diagram.part.EddVisualIDRegistry
					.isCompartmentVisualID(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isSemanticLeafVisualID(int visualID) {
			return es.ucm.fdi.edd.emf.model.edd.diagram.part.EddVisualIDRegistry
					.isSemanticLeafVisualID(visualID);
		}
	};

}

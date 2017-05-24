package es.ucm.fdi.emf.model.ed2.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypeImages;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypes;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import es.ucm.fdi.emf.model.ed2.Ed2Package;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.ED2EditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.ED2TreeElementsEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.LeafEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.ModelEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.NodeEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.NodeLeavesEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.NodeNodesEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.part.Ed2DiagramEditorPlugin;

/**
 * @generated
 */
public class Ed2ElementTypes {

	/**
	 * @generated
	 */
	private Ed2ElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;

	/**
	 * @generated
	 */
	private static DiagramElementTypeImages elementTypeImages = new DiagramElementTypeImages(
			Ed2DiagramEditorPlugin.getInstance()
					.getItemProvidersAdapterFactory());

	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType Model_1000 = getElementType("es.ucm.fdi.ed2.emf.diagram.Model_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ED2_2008 = getElementType("es.ucm.fdi.ed2.emf.diagram.ED2_2008"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Node_2006 = getElementType("es.ucm.fdi.ed2.emf.diagram.Node_2006"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Leaf_2007 = getElementType("es.ucm.fdi.ed2.emf.diagram.Leaf_2007"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ED2TreeElements_4001 = getElementType("es.ucm.fdi.ed2.emf.diagram.ED2TreeElements_4001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType NodeNodes_4002 = getElementType("es.ucm.fdi.ed2.emf.diagram.NodeNodes_4002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType NodeLeaves_4003 = getElementType("es.ucm.fdi.ed2.emf.diagram.NodeLeaves_4003"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		return elementTypeImages.getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		return elementTypeImages.getImage(element);
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		return getImageDescriptor(getElement(hint));
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		return getImage(getElement(hint));
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap<IElementType, ENamedElement>();

			elements.put(Model_1000, Ed2Package.eINSTANCE.getModel());

			elements.put(ED2_2008, Ed2Package.eINSTANCE.getED2());

			elements.put(Node_2006, Ed2Package.eINSTANCE.getNode());

			elements.put(Leaf_2007, Ed2Package.eINSTANCE.getLeaf());

			elements.put(ED2TreeElements_4001,
					Ed2Package.eINSTANCE.getED2_TreeElements());

			elements.put(NodeNodes_4002, Ed2Package.eINSTANCE.getNode_Nodes());

			elements.put(NodeLeaves_4003, Ed2Package.eINSTANCE.getNode_Leaves());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(Model_1000);
			KNOWN_ELEMENT_TYPES.add(ED2_2008);
			KNOWN_ELEMENT_TYPES.add(Node_2006);
			KNOWN_ELEMENT_TYPES.add(Leaf_2007);
			KNOWN_ELEMENT_TYPES.add(ED2TreeElements_4001);
			KNOWN_ELEMENT_TYPES.add(NodeNodes_4002);
			KNOWN_ELEMENT_TYPES.add(NodeLeaves_4003);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case ModelEditPart.VISUAL_ID:
			return Model_1000;
		case ED2EditPart.VISUAL_ID:
			return ED2_2008;
		case NodeEditPart.VISUAL_ID:
			return Node_2006;
		case LeafEditPart.VISUAL_ID:
			return Leaf_2007;
		case ED2TreeElementsEditPart.VISUAL_ID:
			return ED2TreeElements_4001;
		case NodeNodesEditPart.VISUAL_ID:
			return NodeNodes_4002;
		case NodeLeavesEditPart.VISUAL_ID:
			return NodeLeaves_4003;
		}
		return null;
	}

	/**
	 * @generated
	 */
	public static final DiagramElementTypes TYPED_INSTANCE = new DiagramElementTypes(
			elementTypeImages) {

		/**
		 * @generated
		 */
		@Override
		public boolean isKnownElementType(IElementType elementType) {
			return es.ucm.fdi.emf.model.ed2.diagram.providers.Ed2ElementTypes
					.isKnownElementType(elementType);
		}

		/**
		 * @generated
		 */
		@Override
		public IElementType getElementTypeForVisualId(int visualID) {
			return es.ucm.fdi.emf.model.ed2.diagram.providers.Ed2ElementTypes
					.getElementType(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public ENamedElement getDefiningNamedElement(
				IAdaptable elementTypeAdapter) {
			return es.ucm.fdi.emf.model.ed2.diagram.providers.Ed2ElementTypes
					.getElement(elementTypeAdapter);
		}
	};

}

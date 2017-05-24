package es.ucm.fdi.edd.emf.model.edd.diagram.providers;

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

import es.ucm.fdi.edd.emf.model.edd.EddPackage;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.EDDEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.LeafEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.NodeChildrenEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.NodeEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.TreeElementEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.part.EddDiagramEditorPlugin;

/**
 * @generated
 */
public class EddElementTypes {

	/**
	 * @generated
	 */
	private EddElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;

	/**
	 * @generated
	 */
	private static DiagramElementTypeImages elementTypeImages = new DiagramElementTypeImages(
			EddDiagramEditorPlugin.getInstance()
					.getItemProvidersAdapterFactory());

	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType EDD_1000 = getElementType("es.ucm.fdi.edd.emf.diagram.EDD_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TreeElement_2003 = getElementType("es.ucm.fdi.edd.emf.diagram.TreeElement_2003"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Node_2001 = getElementType("es.ucm.fdi.edd.emf.diagram.Node_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Leaf_2002 = getElementType("es.ucm.fdi.edd.emf.diagram.Leaf_2002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType NodeChildren_4001 = getElementType("es.ucm.fdi.edd.emf.diagram.NodeChildren_4001"); //$NON-NLS-1$

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

			elements.put(EDD_1000, EddPackage.eINSTANCE.getEDD());

			elements.put(TreeElement_2003,
					EddPackage.eINSTANCE.getTreeElement());

			elements.put(Node_2001, EddPackage.eINSTANCE.getNode());

			elements.put(Leaf_2002, EddPackage.eINSTANCE.getLeaf());

			elements.put(NodeChildren_4001,
					EddPackage.eINSTANCE.getNode_Children());
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
			KNOWN_ELEMENT_TYPES.add(EDD_1000);
			KNOWN_ELEMENT_TYPES.add(TreeElement_2003);
			KNOWN_ELEMENT_TYPES.add(Node_2001);
			KNOWN_ELEMENT_TYPES.add(Leaf_2002);
			KNOWN_ELEMENT_TYPES.add(NodeChildren_4001);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case EDDEditPart.VISUAL_ID:
			return EDD_1000;
		case TreeElementEditPart.VISUAL_ID:
			return TreeElement_2003;
		case NodeEditPart.VISUAL_ID:
			return Node_2001;
		case LeafEditPart.VISUAL_ID:
			return Leaf_2002;
		case NodeChildrenEditPart.VISUAL_ID:
			return NodeChildren_4001;
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
			return es.ucm.fdi.edd.emf.model.edd.diagram.providers.EddElementTypes
					.isKnownElementType(elementType);
		}

		/**
		 * @generated
		 */
		@Override
		public IElementType getElementTypeForVisualId(int visualID) {
			return es.ucm.fdi.edd.emf.model.edd.diagram.providers.EddElementTypes
					.getElementType(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public ENamedElement getDefiningNamedElement(
				IAdaptable elementTypeAdapter) {
			return es.ucm.fdi.edd.emf.model.edd.diagram.providers.EddElementTypes
					.getElement(elementTypeAdapter);
		}
	};

}

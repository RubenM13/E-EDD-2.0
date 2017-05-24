package es.ucm.fdi.gmf.edd.diagram.providers;

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

import es.ucm.fdi.gmf.edd.EddPackage;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.BlockEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.DiagramEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.ModelEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.TreeElementEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.TreeElementLinksEditPart;
import es.ucm.fdi.gmf.edd.diagram.part.EddDiagramEditorPlugin;

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
	public static final IElementType Diagram_1000 = getElementType("es.ucm.fdi.edd.gmf.diagram.Diagram_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Model_2001 = getElementType("es.ucm.fdi.edd.gmf.diagram.Model_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Block_3001 = getElementType("es.ucm.fdi.edd.gmf.diagram.Block_3001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TreeElement_3002 = getElementType("es.ucm.fdi.edd.gmf.diagram.TreeElement_3002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TreeElementLinks_4001 = getElementType("es.ucm.fdi.edd.gmf.diagram.TreeElementLinks_4001"); //$NON-NLS-1$

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

			elements.put(Diagram_1000, EddPackage.eINSTANCE.getDiagram());

			elements.put(Model_2001, EddPackage.eINSTANCE.getModel());

			elements.put(Block_3001, EddPackage.eINSTANCE.getBlock());

			elements.put(TreeElement_3002,
					EddPackage.eINSTANCE.getTreeElement());

			elements.put(TreeElementLinks_4001,
					EddPackage.eINSTANCE.getTreeElement_Links());
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
			KNOWN_ELEMENT_TYPES.add(Diagram_1000);
			KNOWN_ELEMENT_TYPES.add(Model_2001);
			KNOWN_ELEMENT_TYPES.add(Block_3001);
			KNOWN_ELEMENT_TYPES.add(TreeElement_3002);
			KNOWN_ELEMENT_TYPES.add(TreeElementLinks_4001);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case DiagramEditPart.VISUAL_ID:
			return Diagram_1000;
		case ModelEditPart.VISUAL_ID:
			return Model_2001;
		case BlockEditPart.VISUAL_ID:
			return Block_3001;
		case TreeElementEditPart.VISUAL_ID:
			return TreeElement_3002;
		case TreeElementLinksEditPart.VISUAL_ID:
			return TreeElementLinks_4001;
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
			return es.ucm.fdi.gmf.edd.diagram.providers.EddElementTypes
					.isKnownElementType(elementType);
		}

		/**
		 * @generated
		 */
		@Override
		public IElementType getElementTypeForVisualId(int visualID) {
			return es.ucm.fdi.gmf.edd.diagram.providers.EddElementTypes
					.getElementType(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public ENamedElement getDefiningNamedElement(
				IAdaptable elementTypeAdapter) {
			return es.ucm.fdi.gmf.edd.diagram.providers.EddElementTypes
					.getElement(elementTypeAdapter);
		}
	};

}

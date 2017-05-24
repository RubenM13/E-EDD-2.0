package es.ucm.fdi.edd.emf.model.edd.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.LeafEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.providers.EddElementTypes;
import es.ucm.fdi.edd.emf.model.edd.diagram.providers.EddModelingAssistantProvider;

/**
 * @generated
 */
public class EddModelingAssistantProviderOfLeafEditPart extends
		EddModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnTarget((LeafEditPart) targetEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnTarget(LeafEditPart target) {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(EddElementTypes.NodeChildren_4001);
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForSource(IAdaptable target,
			IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForSource((LeafEditPart) targetEditPart,
				relationshipType);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetTypesForSource(LeafEditPart target,
			IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == EddElementTypes.NodeChildren_4001) {
			types.add(EddElementTypes.Node_2001);
		}
		return types;
	}

}

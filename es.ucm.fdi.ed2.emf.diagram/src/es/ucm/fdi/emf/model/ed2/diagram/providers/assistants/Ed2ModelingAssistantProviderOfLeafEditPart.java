package es.ucm.fdi.emf.model.ed2.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.LeafEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.providers.Ed2ElementTypes;
import es.ucm.fdi.emf.model.ed2.diagram.providers.Ed2ModelingAssistantProvider;

/**
 * @generated
 */
public class Ed2ModelingAssistantProviderOfLeafEditPart extends
		Ed2ModelingAssistantProvider {

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
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(Ed2ElementTypes.ED2TreeElements_4001);
		types.add(Ed2ElementTypes.NodeLeaves_4003);
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
		if (relationshipType == Ed2ElementTypes.ED2TreeElements_4001) {
			types.add(Ed2ElementTypes.ED2_2008);
		} else if (relationshipType == Ed2ElementTypes.NodeLeaves_4003) {
			types.add(Ed2ElementTypes.Node_2006);
		}
		return types;
	}

}

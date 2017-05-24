package es.ucm.fdi.emf.model.ed2.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.ED2EditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.LeafEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.NodeEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.providers.Ed2ElementTypes;
import es.ucm.fdi.emf.model.ed2.diagram.providers.Ed2ModelingAssistantProvider;

/**
 * @generated
 */
public class Ed2ModelingAssistantProviderOfED2EditPart extends
		Ed2ModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSource((ED2EditPart) sourceEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnSource(ED2EditPart source) {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(Ed2ElementTypes.ED2TreeElements_4001);
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnSourceAndTarget(IAdaptable source,
			IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSourceAndTarget((ED2EditPart) sourceEditPart,
				targetEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnSourceAndTarget(
			ED2EditPart source, IGraphicalEditPart targetEditPart) {
		List<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof NodeEditPart) {
			types.add(Ed2ElementTypes.ED2TreeElements_4001);
		}
		if (targetEditPart instanceof LeafEditPart) {
			types.add(Ed2ElementTypes.ED2TreeElements_4001);
		}
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForTarget(IAdaptable source,
			IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForTarget((ED2EditPart) sourceEditPart,
				relationshipType);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetTypesForTarget(ED2EditPart source,
			IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == Ed2ElementTypes.ED2TreeElements_4001) {
			types.add(Ed2ElementTypes.Node_2006);
			types.add(Ed2ElementTypes.Leaf_2007);
		}
		return types;
	}

}

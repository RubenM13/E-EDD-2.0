package es.ucm.fdi.emf.model.ed2.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.LeafEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.NodeEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.providers.Ed2ElementTypes;
import es.ucm.fdi.emf.model.ed2.diagram.providers.Ed2ModelingAssistantProvider;

/**
 * @generated
 */
public class Ed2ModelingAssistantProviderOfNodeEditPart extends
		Ed2ModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSource((NodeEditPart) sourceEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnSource(NodeEditPart source) {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(Ed2ElementTypes.NodeNodes_4002);
		types.add(Ed2ElementTypes.NodeLeaves_4003);
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
		return doGetRelTypesOnSourceAndTarget((NodeEditPart) sourceEditPart,
				targetEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnSourceAndTarget(
			NodeEditPart source, IGraphicalEditPart targetEditPart) {
		List<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof NodeEditPart) {
			types.add(Ed2ElementTypes.NodeNodes_4002);
		}
		if (targetEditPart instanceof LeafEditPart) {
			types.add(Ed2ElementTypes.NodeLeaves_4003);
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
		return doGetTypesForTarget((NodeEditPart) sourceEditPart,
				relationshipType);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetTypesForTarget(NodeEditPart source,
			IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == Ed2ElementTypes.NodeNodes_4002) {
			types.add(Ed2ElementTypes.Node_2006);
		} else if (relationshipType == Ed2ElementTypes.NodeLeaves_4003) {
			types.add(Ed2ElementTypes.Leaf_2007);
		}
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnTarget((NodeEditPart) targetEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnTarget(NodeEditPart target) {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(Ed2ElementTypes.ED2TreeElements_4001);
		types.add(Ed2ElementTypes.NodeNodes_4002);
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
		return doGetTypesForSource((NodeEditPart) targetEditPart,
				relationshipType);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetTypesForSource(NodeEditPart target,
			IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == Ed2ElementTypes.ED2TreeElements_4001) {
			types.add(Ed2ElementTypes.ED2_2008);
		} else if (relationshipType == Ed2ElementTypes.NodeNodes_4002) {
			types.add(Ed2ElementTypes.Node_2006);
		}
		return types;
	}

}

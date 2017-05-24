package es.ucm.fdi.edd.emf.model.edd.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.LeafEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.NodeEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.edit.parts.TreeElementEditPart;
import es.ucm.fdi.edd.emf.model.edd.diagram.providers.EddElementTypes;
import es.ucm.fdi.edd.emf.model.edd.diagram.providers.EddModelingAssistantProvider;

/**
 * @generated
 */
public class EddModelingAssistantProviderOfNodeEditPart extends
		EddModelingAssistantProvider {

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
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(EddElementTypes.NodeChildren_4001);
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
		if (targetEditPart instanceof TreeElementEditPart) {
			types.add(EddElementTypes.NodeChildren_4001);
		}
		if (targetEditPart instanceof NodeEditPart) {
			types.add(EddElementTypes.NodeChildren_4001);
		}
		if (targetEditPart instanceof LeafEditPart) {
			types.add(EddElementTypes.NodeChildren_4001);
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
		if (relationshipType == EddElementTypes.NodeChildren_4001) {
			types.add(EddElementTypes.TreeElement_2003);
			types.add(EddElementTypes.Node_2001);
			types.add(EddElementTypes.Leaf_2002);
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
		return doGetTypesForSource((NodeEditPart) targetEditPart,
				relationshipType);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetTypesForSource(NodeEditPart target,
			IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == EddElementTypes.NodeChildren_4001) {
			types.add(EddElementTypes.Node_2001);
		}
		return types;
	}

}

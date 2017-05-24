package es.ucm.fdi.emf.model.ed2.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import es.ucm.fdi.emf.model.ed2.diagram.providers.Ed2ElementTypes;
import es.ucm.fdi.emf.model.ed2.diagram.providers.Ed2ModelingAssistantProvider;

/**
 * @generated
 */
public class Ed2ModelingAssistantProviderOfModelEditPart extends
		Ed2ModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForPopupBar(IAdaptable host) {
		List<IElementType> types = new ArrayList<IElementType>(3);
		types.add(Ed2ElementTypes.ED2_2008);
		types.add(Ed2ElementTypes.Node_2006);
		types.add(Ed2ElementTypes.Leaf_2007);
		return types;
	}

}

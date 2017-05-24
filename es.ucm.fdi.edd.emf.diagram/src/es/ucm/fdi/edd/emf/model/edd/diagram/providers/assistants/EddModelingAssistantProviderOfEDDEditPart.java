package es.ucm.fdi.edd.emf.model.edd.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import es.ucm.fdi.edd.emf.model.edd.diagram.providers.EddElementTypes;
import es.ucm.fdi.edd.emf.model.edd.diagram.providers.EddModelingAssistantProvider;

/**
 * @generated
 */
public class EddModelingAssistantProviderOfEDDEditPart extends
		EddModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForPopupBar(IAdaptable host) {
		List<IElementType> types = new ArrayList<IElementType>(3);
		types.add(EddElementTypes.TreeElement_2003);
		types.add(EddElementTypes.Node_2001);
		types.add(EddElementTypes.Leaf_2002);
		return types;
	}

}

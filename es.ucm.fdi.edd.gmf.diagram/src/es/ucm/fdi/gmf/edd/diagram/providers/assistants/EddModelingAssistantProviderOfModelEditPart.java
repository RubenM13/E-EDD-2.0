package es.ucm.fdi.gmf.edd.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import es.ucm.fdi.gmf.edd.diagram.providers.EddElementTypes;
import es.ucm.fdi.gmf.edd.diagram.providers.EddModelingAssistantProvider;

/**
 * @generated
 */
public class EddModelingAssistantProviderOfModelEditPart extends
		EddModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForPopupBar(IAdaptable host) {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(EddElementTypes.Block_3001);
		return types;
	}

}

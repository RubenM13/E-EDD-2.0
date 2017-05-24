package es.ucm.fdi.emf.model.ed2.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

import es.ucm.fdi.emf.model.ed2.Ed2Package;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.ED2NameEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.LeafNameEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.edit.parts.NodeNameEditPart;
import es.ucm.fdi.emf.model.ed2.diagram.parsers.MessageFormatParser;
import es.ucm.fdi.emf.model.ed2.diagram.part.Ed2VisualIDRegistry;

/**
 * @generated
 */
public class Ed2ParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser eD2Name_5010Parser;

	/**
	 * @generated
	 */
	private IParser getED2Name_5010Parser() {
		if (eD2Name_5010Parser == null) {
			EAttribute[] features = new EAttribute[] { Ed2Package.eINSTANCE
					.getED2_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			eD2Name_5010Parser = parser;
		}
		return eD2Name_5010Parser;
	}

	/**
	 * @generated
	 */
	private IParser nodeName_5008Parser;

	/**
	 * @generated
	 */
	private IParser getNodeName_5008Parser() {
		if (nodeName_5008Parser == null) {
			EAttribute[] features = new EAttribute[] { Ed2Package.eINSTANCE
					.getTreeElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			nodeName_5008Parser = parser;
		}
		return nodeName_5008Parser;
	}

	/**
	 * @generated
	 */
	private IParser leafName_5009Parser;

	/**
	 * @generated
	 */
	private IParser getLeafName_5009Parser() {
		if (leafName_5009Parser == null) {
			EAttribute[] features = new EAttribute[] { Ed2Package.eINSTANCE
					.getTreeElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			leafName_5009Parser = parser;
		}
		return leafName_5009Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case ED2NameEditPart.VISUAL_ID:
			return getED2Name_5010Parser();
		case NodeNameEditPart.VISUAL_ID:
			return getNodeName_5008Parser();
		case LeafNameEditPart.VISUAL_ID:
			return getLeafName_5009Parser();
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * @generated
	 */
	public static IParser getParser(IElementType type, EObject object,
			String parserHint) {
		return ParserService.getInstance().getParser(
				new HintAdapter(type, object, parserHint));
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(Ed2VisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(Ed2VisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (Ed2ElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}

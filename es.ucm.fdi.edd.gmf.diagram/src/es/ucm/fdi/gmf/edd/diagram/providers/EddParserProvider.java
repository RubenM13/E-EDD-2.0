package es.ucm.fdi.gmf.edd.diagram.providers;

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

import es.ucm.fdi.gmf.edd.EddPackage;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.BlockNameEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.ModelNameEditPart;
import es.ucm.fdi.gmf.edd.diagram.edit.parts.TreeElementNameEditPart;
import es.ucm.fdi.gmf.edd.diagram.parsers.MessageFormatParser;
import es.ucm.fdi.gmf.edd.diagram.part.EddVisualIDRegistry;

/**
 * @generated
 */
public class EddParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser modelName_5003Parser;

	/**
	 * @generated
	 */
	private IParser getModelName_5003Parser() {
		if (modelName_5003Parser == null) {
			EAttribute[] features = new EAttribute[] { EddPackage.eINSTANCE
					.getModel_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			modelName_5003Parser = parser;
		}
		return modelName_5003Parser;
	}

	/**
	 * @generated
	 */
	private IParser blockName_5002Parser;

	/**
	 * @generated
	 */
	private IParser getBlockName_5002Parser() {
		if (blockName_5002Parser == null) {
			EAttribute[] features = new EAttribute[] { EddPackage.eINSTANCE
					.getBlock_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			blockName_5002Parser = parser;
		}
		return blockName_5002Parser;
	}

	/**
	 * @generated
	 */
	private IParser treeElementName_5001Parser;

	/**
	 * @generated
	 */
	private IParser getTreeElementName_5001Parser() {
		if (treeElementName_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { EddPackage.eINSTANCE
					.getTreeElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			treeElementName_5001Parser = parser;
		}
		return treeElementName_5001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case ModelNameEditPart.VISUAL_ID:
			return getModelName_5003Parser();
		case BlockNameEditPart.VISUAL_ID:
			return getBlockName_5002Parser();
		case TreeElementNameEditPart.VISUAL_ID:
			return getTreeElementName_5001Parser();
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
			return getParser(EddVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(EddVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (EddElementTypes.getElement(hint) == null) {
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

package es.ucm.fdi.edd.ui.views.model;

import org.eclipse.core.databinding.conversion.Converter;

public class CommaSeparatedStringToStringArrayConverter extends Converter {

	private static final String COMMA = ",";

	public CommaSeparatedStringToStringArrayConverter() {
		// Ensure that the fromType is a String and the toType is a String[] array
		super(String.class, String[].class);
	}

	@Override
	public Object convert(Object fromObject) {
		if (fromObject instanceof String) {
			return ((String) fromObject).split(COMMA);
		}
		
		return null;
	}
}
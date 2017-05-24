package es.ucm.fdi.edd.ui.views.model;

import org.eclipse.core.databinding.conversion.Converter;

public class StringArrayToCommaSeparatedStringConverter extends Converter {

	private static final String COMMA = ",";

	public StringArrayToCommaSeparatedStringConverter() {
		// Ensure that the fromType is a String[] array and the toType is a String
		super(String[].class, String.class);
	}

	@Override
	public Object convert(Object fromObject) {
		if (fromObject instanceof String[]) {
			String[] stringArray = (String[]) fromObject;
			StringBuilder sb = new StringBuilder();
			int length = stringArray.length;
			for (int i = 0; i < length; i++) {
				String string = stringArray[i];
				sb.append(string);
				if (i + 1 < length) {
					sb.append(COMMA);
				}
			}
			return sb.toString();
		}
		
		return null;
	}
}
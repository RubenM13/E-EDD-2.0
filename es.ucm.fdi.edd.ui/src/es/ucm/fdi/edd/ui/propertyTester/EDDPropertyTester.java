package es.ucm.fdi.edd.ui.propertyTester;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.expressions.PropertyTester;

/**
 * Tester for the <code>isQuestionPanelVisible</code> and <code>hasContent</code> properties.
 */
public class EDDPropertyTester extends PropertyTester {
	
	/** The separator char. */
	private static final char SEPARATOR = ',';
	
	/** isQuestionPanelVisible property. */
	protected static final String IS_QUESTION_PANEL_VISIBLE = "isQuestionPanelVisible";
	/** hasContent property. */
	protected static final String HAS_CONTENT = "hasContent";

	/**
	 * Creates a new instance of {@link EDDPropertyTester}.
	 */
	public EDDPropertyTester() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (IS_QUESTION_PANEL_VISIBLE.equals(property)) {
			if (receiver instanceof String && expectedValue instanceof String) {
				return checkStatus((String)receiver, (String)expectedValue);
			}
		} else if (HAS_CONTENT.equals(property)) {
			if (receiver instanceof String && expectedValue instanceof String) {
				return checkStatus((String)receiver, (String)expectedValue);
			}
		}
		
		return false;
	}

	/**
	 * Checks if the state of the current object matches the expected one.
	 * 
	 * @param receiver 
	 * 				the receiver
	 * @param expectedValue 
	 * 				the expected status
	 * 
	 * @return <code>true</code> if the state match, <code>false</code> otherwise
	 */
	private boolean checkStatus(String receiver, String expectedValue) {
		return checkValue(receiver, expectedValue);
	}

	/**
	 * Checks a candidate string against a comma-separated list of values.
	 *
	 * @param candidate
	 *            the candidate string
	 * @param values
	 *            the potential valid values, separated by commas
	 *
	 * @return <code>true</code> if the candidate is contained in the values, <code>false</code> otherwise
	 */
	private boolean checkValue(final String candidate, final String values) {
		for (final String value : StringUtils.split(values, SEPARATOR)) {
			if (value.equalsIgnoreCase(candidate)) {
				return true;
			}
		}

		return false;
	}
}
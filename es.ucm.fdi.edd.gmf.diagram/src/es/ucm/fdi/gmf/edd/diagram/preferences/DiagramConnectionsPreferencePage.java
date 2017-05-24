package es.ucm.fdi.gmf.edd.diagram.preferences;

import org.eclipse.gmf.runtime.diagram.ui.preferences.ConnectionsPreferencePage;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.jface.preference.IPreferenceStore;

import es.ucm.fdi.gmf.edd.diagram.part.EddDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramConnectionsPreferencePage extends ConnectionsPreferencePage {

	/**
	 * @generated
	 */
	public DiagramConnectionsPreferencePage() {
		setPreferenceStore(EddDiagramEditorPlugin.getInstance()
				.getPreferenceStore());
	}

	/**
	 * @generated NOT
	 */
	public static void initDefaults(IPreferenceStore preferenceStore) {
		// FIXME Joel 
		preferenceStore.setDefault(IPreferenceConstants.PREF_LINE_STYLE,
				Routing.RECTILINEAR);
	}
}

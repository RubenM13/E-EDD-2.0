package es.ucm.fdi.edd.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import es.ucm.fdi.edd.ui.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {
	
	public PreferenceInitializer() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
//		store.setDefault(PreferenceConstants.JSERVER_PATH, "D:/workspace/git/edd/ebin");
		store.setDefault(PreferenceConstants.JSERVER_PATH, "C:/Users/Ruben/Workspace/edd-master");
		store.setDefault(PreferenceConstants.UMLGRAPH_PATH, "%UMLGRAPH_HOME%");
		//-------------------------------------------------------------------
		store.setDefault(PreferenceConstants.P_BOOLEAN, true);
		store.setDefault(PreferenceConstants.P_CHOICE, "choice2");
		store.setDefault(PreferenceConstants.P_STRING, "Default value");
	}
}

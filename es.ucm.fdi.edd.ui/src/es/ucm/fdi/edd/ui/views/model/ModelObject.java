package es.ucm.fdi.edd.ui.views.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelObject implements PropertyChangeListener {
	
	private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	/**
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	/**
	 * @param propertyName
	 * @param oldValue
	 * @param newValue
	 */
	protected void firePropertyChange(String propertyName, Object oldValue,	Object newValue) {
		changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}
	
	public PropertyChangeSupport getChangeSupport() {
		return changeSupport;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object oldValue = evt.getOldValue();
		Object newValue = evt.getNewValue();
		String propertyName = evt.getPropertyName();
		changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}
}
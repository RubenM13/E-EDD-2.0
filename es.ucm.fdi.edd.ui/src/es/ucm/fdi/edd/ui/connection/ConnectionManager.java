package es.ucm.fdi.edd.ui.connection;

import java.util.Observable;

/**
 * The {@link ConnectionManager} will notify its subscribers upon connection/disconnection.
 */
public class ConnectionManager extends Observable {

	private boolean connected;

	private static final ConnectionManager instance = new ConnectionManager();

	/**
	 * 
	 */
	private ConnectionManager() {
		// empty
	}

	/**
	 * @return
	 */
	public static ConnectionManager getInstance() {
		return instance;
	}

	/**
	 * @return
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * 
	 */
	public void connect() {
		connected = true;
		setChanged();
		notifyObservers();

	}

	/**
	 * 
	 */
	public void disconnect() {
		connected = false;
		setChanged();
		notifyObservers();
	}
}
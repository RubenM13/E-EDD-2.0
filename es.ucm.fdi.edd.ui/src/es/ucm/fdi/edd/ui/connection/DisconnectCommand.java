package es.ucm.fdi.edd.ui.connection;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * {@link DisconnectComand} which corresponds to "disconnect" action, subscribes
 * to listen {@link ConnectionManager} state changes and sets its sate by
 * calling <code>setBaseEnabled</code>. At the beginning, its sate is disabled:
 * <code>setBaseEnabled(false)</code>.
 */
public class DisconnectCommand extends AbstractHandler implements Observer {

	public DisconnectCommand() {
		ConnectionManager.getInstance().addObserver(this);
		setBaseEnabled(false);
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ConnectionManager.getInstance().disconnect();
		return null;
	}

	@Override
	public void update(Observable o, Object arg) {
		ConnectionManager connectionManager = (ConnectionManager) o;
		setBaseEnabled(connectionManager.isConnected());
	}
}
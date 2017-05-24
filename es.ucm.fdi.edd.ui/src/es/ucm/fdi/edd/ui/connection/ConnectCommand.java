package es.ucm.fdi.edd.ui.connection;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * {@link ConnectComand} which corresponds to "connect" action, subscribes to
 * listen {@link ConnectionManager} state changes and sets its sate by calling
 * <code>setBaseEnabled<code>.
 */
public class ConnectCommand extends AbstractHandler implements Observer {

	public ConnectCommand() {
		ConnectionManager.getInstance().addObserver(this);
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ConnectionManager.getInstance().connect();
		return null;
	}

	@Override
	public void update(Observable o, Object arg) {
		ConnectionManager connectionManager = (ConnectionManager) o;
		setBaseEnabled(!connectionManager.isConnected());
	}
}
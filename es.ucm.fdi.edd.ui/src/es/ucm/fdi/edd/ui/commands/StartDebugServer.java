package es.ucm.fdi.edd.ui.commands;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import es.ucm.fdi.edd.ui.connection.ConnectionManager;
import es.ucm.fdi.edd.ui.views.EDDebugView;

/**
 * {@link StartDebugServer} which corresponds to "connect" action, subscribes to
 * listen {@link ConnectionManager} state changes and sets its sate by calling
 * <code>setBaseEnabled<code>.
 */
public class StartDebugServer extends AbstractHandler implements Observer {
	
	private static final String WS_EDD = "edd";
	
	/**
	 * 
	 */
	public StartDebugServer() {
		super();
		ConnectionManager.getInstance().addObserver(this);
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow activeWorkbenchWindow = HandlerUtil.getActiveWorkbenchWindow(event);
		Shell shell = activeWorkbenchWindow.getShell();
		IViewPart part = activeWorkbenchWindow.getActivePage().findView(EDDebugView.VIEW_ID);
		if (part instanceof EDDebugView) {
			EDDebugView view = (EDDebugView)part;
			String buggyCall = view.getBuggyCall();
			IFile debugFile = view.getDebugFile();
			if (debugFile != null && debugFile.isAccessible() && buggyCall != null && !buggyCall.isEmpty()) {
				createEDDFolder(debugFile);
				boolean result = view.startServer();
				if (result) {
					ConnectionManager.getInstance().connect();
				}
				else {
					MessageDialog.openError(shell, "EDD - Error", "EDD server cannot be correctly initialized...");
				}
			}
			else {
				MessageDialog.openError(shell, "EDD - Error", "You must provide the buggy call and the location of the erlang source file before to start debugging.");
			}
		}
		
		return null;
	}	
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
    public void update(Observable o, Object arg) {
        ConnectionManager connectionManager = (ConnectionManager) o;
        setBaseEnabled(!connectionManager.isConnected());
	}
	
	/**
	 * Create the working directory.
	 */
	private void createEDDFolder(IFile iFile) {
		if (iFile == null || !iFile.exists())
			return;
		
		try {
			IProject project = iFile.getProject();
			IFolder folder = project.getFolder(WS_EDD);
			if (!folder.exists()) {
				folder.create(IResource.NONE, true, new NullProgressMonitor());
			}
			else {
				folder.refreshLocal(IResource.DEPTH_INFINITE, null);
			}
		}
		catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	private void test(IFile debugFile) {
		String name = debugFile.getName();
		String extension = debugFile.getFileExtension();
		IPath location = debugFile.getLocation();
		IContainer parent = debugFile.getParent();
		IProject project = debugFile.getProject();
		String srcName = debugFile.getName();
		String binName = srcName.replace(".erl", ".beam");
		
		String srcPath = debugFile.getLocation().toPortableString();
		String binPath1 = srcPath.replace("/src/", "/ebin/");
		String binPath2 = binPath1.replace(".erl", ".beam");
		
		IProject iProject = debugFile.getProject();
//				IResource beamFile1 = iProject.getFile(binName);
//				System.out.println(beamFile1);
		
		IResource beamFile = iProject.findMember(binPath2);
		//D:\workspace\runtime-tests\EDDAckermann\ebin\ackermann.beam
		System.out.println(beamFile);
	}
}
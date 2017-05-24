package es.ucm.fdi.edd.ui.utils;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

import es.ucm.fdi.edd.core.exception.EDDException;
import es.ucm.fdi.edd.core.json.model.JsonDocument;
import es.ucm.fdi.edd.core.json.utils.JsonUtils;

/**
 * Lazy instantiation of Singleton Pattern.
 */
public class FilesManager {
	
	private static final String WS_EDD = "edd";

	/** The private singleton instance. */
	private static FilesManager fm;

	/**
	 * The private singleton constructor.
	 */
	private FilesManager() {
		// empty
	}

	/**
	 * Gets the singleton instance.
	 * 
	 * @return the instance.
	 */
	public static FilesManager getInstance() {
		if (fm == null) {
			synchronized (FilesManager.class) {
				if (fm == null) {
					// instance will be created at request time
					fm = new FilesManager();
				}
			}
		}

		return fm;
	}
	
	/**
	 * Create the working directory.
	 */
	public void createEDDFolder(IFile debugFile) {
		if (debugFile == null || !debugFile.exists())
			return;
		
		try {
			IProject project = debugFile.getProject();
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
	
	public void writeJsonDocument(JsonDocument document, String path) throws EDDException {
		if (document == null) {
			throw new EDDException("The json document must not be null");
		}
		
		try {
			IResource debugFile = null;
			IProject project = debugFile.getProject();
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
		
		
		
		
		try {
			JsonUtils jsu = new JsonUtils();
			jsu.toFile(document, path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

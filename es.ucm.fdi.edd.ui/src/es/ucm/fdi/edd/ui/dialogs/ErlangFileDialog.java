package es.ucm.fdi.edd.ui.dialogs;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import es.ucm.fdi.edd.ui.Activator;

/**
 *	Erlang file selection dialog.
 */
public class ErlangFileDialog extends ElementTreeSelectionDialog {
	
	private static final String TITLE = "Open File";
	private static final String MESSAGE = "Choose an Erlang file for debug";
	
	public ErlangFileDialog(Shell parent) {
		super(parent, new WorkbenchLabelProvider(), new BaseWorkbenchContentProvider());
		initialize();
	}

	private void initialize() {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		
		setTitle(TITLE);
		setMessage(MESSAGE);
	    setInput(root);
	    setAllowMultiple(false);
	    setValidator(new ErlangStatusValidator());
	    addFilter(new ErlangViewerFilter());
	}
	
	@Override
	public Object getFirstResult() {
		Object selected = super.getFirstResult();
		if (selected instanceof IFile) {
			IFile iFile = (IFile) selected;
		}
		
		return selected;
	}
}

/**
 * Erlang status validator.
 */
final class ErlangStatusValidator implements ISelectionStatusValidator {
	@Override
	public IStatus validate(Object[] selection) {
	    if (selection != null && selection.length == 1) {
	        if (selection[0] instanceof IFile) {
	            return new Status(IStatus.OK, Activator.PLUGIN_ID, ((IFile)selection[0]).getFullPath().toString());
	        }
	    }
	    return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Please select a valid *.erl file.");
	}
}

/**
 * Erlang viewer filter.
 */
final class ErlangViewerFilter extends ViewerFilter {
	
	private static final String ERLIDE_NATURE = "org.erlide.core.erlnature";
	private static final String ERL = "erl";

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		try {
			if (element instanceof IProject) {
				IProject iProject = (IProject)element;
				return iProject.hasNature(ERLIDE_NATURE);
			}
			if (element instanceof IFolder) {
				IFolder iFolder = (IFolder)element;
				IResource[] members;
				members = iFolder.members();
//				return members.length > 0;
				boolean hasErlangFiles = false;
				for (IResource iResource : members) {
					String extension = iResource.getFileExtension();
					if (extension != null && extension.equals(ERL)) {
						hasErlangFiles = true;
					}
				}
				return hasErlangFiles;
			}

			if (element instanceof IFile) {
				IFile iFile = (IFile)element;
				String extension = iFile.getFileExtension();
				return extension == null ? false : extension.equals(ERL);	
			}
		} catch (CoreException e) {
			return false;
		}
		
		return true;
	}
}
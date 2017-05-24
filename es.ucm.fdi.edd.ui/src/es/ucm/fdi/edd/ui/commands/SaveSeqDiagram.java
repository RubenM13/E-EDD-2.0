package es.ucm.fdi.edd.ui.commands;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import es.ucm.fdi.edd.ui.views.EddSequenceView;

public class SaveSeqDiagram extends AbstractHandler {

	private static final String[] VALID_EXTENSIONS = {"jpg", "png", "gif", "tif", "bmp"};
	private static final int[] VALID_FORMATS = {SWT.IMAGE_JPEG, SWT.IMAGE_PNG, SWT.IMAGE_GIF, SWT.IMAGE_TIFF, SWT.IMAGE_BMP};
	private static final String[] VALID_EXTENSION_MASKS;

	static {
		VALID_EXTENSION_MASKS = new String[VALID_FORMATS.length];
		for (int i = 0; i < VALID_EXTENSION_MASKS.length; i++)
			VALID_EXTENSION_MASKS [i] = "*."+VALID_EXTENSIONS[i];			
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart activePart = HandlerUtil.getActivePart(event);
		if (activePart instanceof EddSequenceView) {
			Command command = event.getCommand();
			executeCommand(command, (EddSequenceView) activePart);
		}
		
		return null;
	}
	
	public void executeCommand(Command command, EddSequenceView view) {
//		IFile selectedFile = null;
//		if (selectedFile == null)
//			return;
		boolean pathIsValid = false;
		IPath path = null;
//		int fileFormat = 0;
		while (!pathIsValid ) {
			FileDialog saveDialog = new FileDialog(Display.getCurrent().getActiveShell(), SWT.SAVE);
			saveDialog.setText("Choose a location to save to");
//			saveDialog.setFileName(selectedFile.getLocation().removeFileExtension().lastSegment());
			saveDialog.setFilterExtensions(VALID_EXTENSION_MASKS);
			String pathString = saveDialog.open();
			if (pathString == null)
				return;
			path = Path.fromOSString(pathString);
			if (path.toFile().isDirectory()) {
				MessageDialog.openError(null, "Invalid file path", "Location is already in use by a directory");
				continue;
			}
			String extension = path.getFileExtension();
			List<String> validExtensionsList = Arrays.asList(VALID_EXTENSIONS);
			if (extension == null || !validExtensionsList.contains(extension.toLowerCase())) {
				MessageDialog.openError(null, "Invalid file extension", "Supported file formats are: " + validExtensionsList);
				continue;
			}
//			fileFormat = VALID_FORMATS[validExtensionsList.indexOf(extension.toLowerCase())];
			File parentDir = path.toFile().getParentFile();
			parentDir.mkdirs();
			if (parentDir.isDirectory())
				pathIsValid = true;
			else
				MessageDialog.openError(null, "Invalid file path", "Could not create directory");
		}
		view.saveImage(path);
	}
}

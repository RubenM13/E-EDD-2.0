package es.ucm.fdi.edd.ui.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import es.ucm.fdi.edd.ui.views.GraphvizView;
import es.ucm.fdi.edd.ui.views.IToolBarAction;

public class PushActionDelegateTestCommand extends AbstractHandler {

	/** pointer to image view */
	private IToolBarAction view;
	
	private String currentDir = ""; /* remembering file open directory */

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart activePart = HandlerUtil.getActivePart(event);
		if (activePart instanceof IToolBarAction) {
			this.view = (IToolBarAction) activePart;
			
			// Command command = event.getCommand();
			String actionName = event.getParameter("es.ucm.fdi.edd.ui.commands.pushActionDelegateTest.commandParameterActionName");
			executeCommand(actionName);
		}
		
		return null;
	}
	
	public void executeCommand(String id) {
		if (id.equals("toolbar.open")) {
			onFileOpen();
			return;
		}
		if (id.equals("toolbar.zoomin")) {
			view.zoomIn();
			return;
		} else if (id.equals("toolbar.zoomout")) {
			view.zoomOut();
			return;
		}else if (id.equals("toolbar.fit")) {
			view.fitCanvas();
			return;
		} else if (id.equals("toolbar.rotate")) {
			/* rotate image anti-clockwise */
			ImageData src = view.getImageData();
			if (src == null)
				return;
			PaletteData srcPal = src.palette;
			PaletteData destPal;
			ImageData dest;
			/* construct a new ImageData */
			if (srcPal.isDirect) {
				destPal = new PaletteData(srcPal.redMask, srcPal.greenMask,
						srcPal.blueMask);
			} else {
				destPal = new PaletteData(srcPal.getRGBs());
			}
			dest = new ImageData(src.height, src.width, src.depth, destPal);
			/* rotate by rearranging the pixels */
			for (int i = 0; i < src.width; i++) {
				for (int j = 0; j < src.height; j++) {
					int pixel = src.getPixel(i, j);
					dest.setPixel(j, src.width - 1 - i, pixel);
				}
			}
			view.setImageData(dest);
			return;
		} else if (id.equals("toolbar.original")) {
			view.showOriginal();
			return;
		}
	}
	
	/**
	 * Call back funtion of button "open". Will open a file dialog, and choose
	 * the image file. It supports image formats supported by Eclipse.
	 */
	public void onFileOpen() {
		FileDialog fileChooser = new FileDialog(new Shell(), SWT.OPEN);
		fileChooser.setText("Open image file");
		fileChooser.setFilterPath(currentDir);
		fileChooser.setFilterExtensions(new String[] { "*.gif; *.jpg; *.png; *.ico; *.bmp", "*.dot" });
		fileChooser.setFilterNames(new String[] { "SWT image (gif, jpeg, png, ico, bmp)", "DOT Graphs (dot)" });
		String filename = fileChooser.open();
		if (filename != null) {
			view.loadImage(filename);
			currentDir = fileChooser.getFilterPath();
		}
	}
}

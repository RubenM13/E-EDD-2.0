package es.ucm.fdi.edd.ui.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import es.ucm.fdi.edd.ui.views.EDDViewer;
import es.ucm.fdi.edd.ui.views.utils.SWTImageCanvas;

public class PushActionDelegateCommand extends AbstractHandler {

	private EDDViewer view;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart activePart = HandlerUtil.getActivePart(event);
		if (activePart instanceof EDDViewer) {
			this.view = (EDDViewer) activePart;
			
//			Command command = event.getCommand();
			String actionName = event.getParameter("es.ucm.fdi.edd.ui.commands.pushActionDelegate.commandParameterActionName");
			executeCommand(actionName);
		}
		
		return null;
	}
	
	public void executeCommand(String id) {
		SWTImageCanvas imageCanvas = view.imageCanvas;
		if (imageCanvas != null) {
			if (id.equals("toolbar.open")) {
				imageCanvas.onFileOpen();
				return;
			}
			if (imageCanvas.getSourceImage() == null) {
				System.out.println("the source image is null");
				return;
			}
			if (id.equals("toolbar.zoomin")) {
				imageCanvas.zoomIn();
				return;
			} else if (id.equals("toolbar.zoomout")) {
				imageCanvas.zoomOut();
				return;
			} else if (id.equals("toolbar.fit")) {
				imageCanvas.fitCanvas();
				return;
			} else if (id.equals("toolbar.rotate")) {
				/* rotate image anti-clockwise */
				ImageData src = imageCanvas.getImageData();
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
				imageCanvas.setImageData(dest);
				return;
			} else if (id.equals("toolbar.original")) {
				imageCanvas.showOriginal();
				return;
			}
		}
	}
}

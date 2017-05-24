package es.ucm.fdi.edd.ui.views.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import es.ucm.fdi.edd.ui.views.EDDViewer;
import es.ucm.fdi.edd.ui.views.utils.SWTImageCanvas;

public class PushActionDelegate implements IViewActionDelegate {

	/** pointer to image view */
//	public GraphvizView view = null;
	public EDDViewer view = null;
	/** Action id of this delegate */
	public String id;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	public void init(IViewPart viewPart) {
		if (viewPart instanceof EDDViewer) {
			this.view = (EDDViewer) viewPart;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		String id = action.getId();
		// Canvas imageCanvas = view.getCanvas();
		// Listener listener = new Listener() {
		// int zoomFactor = 50;
		// public void handleEvent(Event event) {
		// switch (event.type) {
		// case SWT.MouseWheel:
		// zoomFactor = Math.max(0, zoomFactor + event.count);
		// Canvas canvas = (Canvas)event.widget;
		// canvas.redraw();
		// break;
		// case SWT.Paint:
		// event.gc.drawText("Zoom = "+zoomFactor, 10, 10);
		// break;
		// }
		// }
		// };
		// imageCanvas.addListener(SWT.MouseWheel, listener);
		// imageCanvas.addListener(SWT.Paint, listener);

//		GraphicalViewer viewer = view.getViewer();
//		SWTImageCanvas imageCanvas = viewer.getCanvas();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action
	 * .IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		// empty
	}
}

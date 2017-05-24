package es.ucm.fdi.edd.ui.views.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import es.ucm.fdi.edd.ui.views.GraphvizView;

public class PushActionDelegateTest implements IViewActionDelegate {

	/** pointer to image view */
	public GraphvizView view = null;
	/** Action id of this delegate */
	public String id;
	
	private String currentDir = ""; /* remembering file open directory */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	public void init(IViewPart viewPart) {
		if (viewPart instanceof GraphvizView) {
			this.view = (GraphvizView) viewPart;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		String id = action.getId();
		 
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

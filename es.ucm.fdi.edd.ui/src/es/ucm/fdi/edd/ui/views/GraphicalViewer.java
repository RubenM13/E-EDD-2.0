package es.ucm.fdi.edd.ui.views;

import java.awt.geom.AffineTransform;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.ConstructorUtils;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ScrollBar;

import com.abstratt.imageviewer.Activator;
import com.abstratt.imageviewer.IGraphicalContentProvider;

import es.ucm.fdi.edd.ui.views.utils.SWT2Dutil;

/**
 * A viewer that knows how to display graphical contents.
 * 
 * @see IGraphicalContentProvider
 */
public class GraphicalViewer extends ContentViewer {

	/* zooming rates in x and y direction are equal. */
	final float ZOOMIN_RATE = 1.1f; /* zoomin rate */
	final float ZOOMOUT_RATE = 0.9f; /* zoomout rate */
	private Image sourceImage; /* original image */
	private Image screenImage; /* screen image */
	private AffineTransform transform = new AffineTransform();
	private Canvas canvas;
	private boolean adjustToCanvas = true;
	private boolean imageRedrawRequested;	

	public GraphicalViewer(Composite parent) {
		canvas = new Canvas(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.NO_BACKGROUND);
		canvas.addControlListener(new ControlAdapter() { /* resize listener. */
			public void controlResized(ControlEvent event) {
				canvas.setBounds(parent.getClientArea());
				requestImageRedraw();
				syncScrollBars();
			}
		});
		canvas.addPaintListener(new PaintListener() { /* paint listener. */
			public void paintControl(final PaintEvent event) {
				redrawImageIfRequested();
				paint(event.gc);
			}
		});
		initScrollBars();
	}
	
	private void requestImageRedraw() {
		imageRedrawRequested = true;
	}
	
	protected void redrawImageIfRequested() {
		if (imageRedrawRequested)
			redrawImage();
		imageRedrawRequested = false;
	}
	
	/* Initalize the scrollbar and register listeners. */
	private void initScrollBars() {
		ScrollBar horizontal = canvas.getHorizontalBar();
		horizontal.setEnabled(false);
		horizontal.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				scrollHorizontally((ScrollBar) event.widget);
			}
		});
		ScrollBar vertical = canvas.getVerticalBar();
		vertical.setEnabled(false);
		vertical.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				scrollVertically((ScrollBar) event.widget);
			}
		});
	}

	/* Scroll horizontally */
	private void scrollHorizontally(ScrollBar scrollBar) {
		if (sourceImage == null)
			return;

		AffineTransform af = transform;
		double tx = af.getTranslateX();
		double select = -scrollBar.getSelection();
		af.preConcatenate(AffineTransform.getTranslateInstance(select - tx, 0));
		transform = af;
		syncScrollBars();
	}

	/* Scroll vertically */
	private void scrollVertically(ScrollBar scrollBar) {
		if (sourceImage == null)
			return;

		AffineTransform af = transform;
		double ty = af.getTranslateY();
		double select = -scrollBar.getSelection();
		af.preConcatenate(AffineTransform.getTranslateInstance(0, select - ty));
		transform = af;
		syncScrollBars();
	}
	
	/**
	 * Synchronize the scrollbar with the image. If the transform is out of
	 * range, it will correct it. This function considers only following factors
	 * :<b> transform, image size, client area</b>.
	 */
	public void syncScrollBars() {
		if (sourceImage == null) {
			canvas.redraw();
			return;
		}

		AffineTransform af = transform;
		double sx = af.getScaleX(), sy = af.getScaleY();
		double tx = af.getTranslateX(), ty = af.getTranslateY();
		if (tx > 0)
			tx = 0;
		if (ty > 0)
			ty = 0;

		ScrollBar horizontal = canvas.getHorizontalBar();
		horizontal.setIncrement((int) (canvas.getClientArea().width / 100));
		horizontal.setPageIncrement(canvas.getClientArea().width);
		Rectangle imageBound = sourceImage.isDisposed() ? canvas.getBounds() : sourceImage.getBounds();
		int cw = canvas.getClientArea().width, ch = canvas.getClientArea().height;
		if (imageBound.width * sx > cw) { /* image is wider than client area */
			horizontal.setMaximum((int) (imageBound.width * sx));
			horizontal.setEnabled(true);
			if (((int) -tx) > horizontal.getMaximum() - cw)
				tx = -horizontal.getMaximum() + cw;
		} else { /* image is narrower than client area */
			horizontal.setEnabled(false);
			tx = (cw - imageBound.width * sx) / 2; // center if too small.
		}
		horizontal.setSelection((int) (-tx));
		horizontal.setThumb((int) (canvas.getClientArea().width));

		ScrollBar vertical = canvas.getVerticalBar();
		vertical.setIncrement((int) (canvas.getClientArea().height / 100));
		vertical.setPageIncrement((int) (canvas.getClientArea().height));
		if (imageBound.height * sy > ch) { /* image is higher than client area */
			vertical.setMaximum((int) (imageBound.height * sy));
			vertical.setEnabled(true);
			if (((int) -ty) > vertical.getMaximum() - ch)
				ty = -vertical.getMaximum() + ch;
		} else { /* image is less higher than client area */
			vertical.setEnabled(false);
			ty = (ch - imageBound.height * sy) / 2; // center if too small.
		}
		vertical.setSelection((int) (-ty));
		vertical.setThumb((int) (canvas.getClientArea().height));

		/* update transform. */
		af = AffineTransform.getScaleInstance(sx, sy);
		af.preConcatenate(AffineTransform.getTranslateInstance(tx, ty));
		transform = af;

		canvas.redraw();
	}
	
	/* Paint function */
	private void paint(GC gc) {
		sourceImage = getImage(canvas.getSize());
		Rectangle clientRect = canvas.getClientArea(); /* Canvas' painting area */
		if (sourceImage != null) {
			Rectangle imageRect = SWT2Dutil.inverseTransformRect(transform, clientRect);
			int gap = 2; /* find a better start point to render */
			imageRect.x -= gap;
			imageRect.y -= gap;
			imageRect.width += 2 * gap;
			imageRect.height += 2 * gap;

			Rectangle imageBound = sourceImage.getBounds();
			imageRect = imageRect.intersection(imageBound);
			Rectangle destRect = SWT2Dutil.transformRect(transform, imageRect);

			if (screenImage != null)
				screenImage.dispose();
			screenImage = new Image(canvas.getDisplay(), clientRect.width, clientRect.height);
			GC newGC = new GC(screenImage);
			newGC.setClipping(clientRect);
			newGC.drawImage(sourceImage, imageRect.x, imageRect.y,
					imageRect.width, imageRect.height, destRect.x, destRect.y,
					destRect.width, destRect.height);
			newGC.dispose();

			gc.drawImage(screenImage, 0, 0);
		} else {
			gc.setClipping(clientRect);
			gc.fillRectangle(clientRect);
			initScrollBars();
		}
	}

	@Override
	public Control getControl() {
		return canvas;
	}

	private Image getImage(Point point) {
		IGraphicalContentProvider provider = (IGraphicalContentProvider) getContentProvider();
		if (provider == null)
			return null;
		return provider.getImage();
	}

	@Override
	public ISelection getSelection() {
		// no selection supported
		return null;
	}

	@Override
	protected void inputChanged(Object newInput, Object oldInput) {
		refresh();
	}

	public boolean isAdjustToCanvas() {
		return adjustToCanvas;
	}

	public void redrawImage() {
		if (getContentProvider() == null)
			return;
		Class<? extends IContentProvider> contentProviderClass = getContentProvider().getClass();
		try {
			setContentProvider((IContentProvider) ConstructorUtils.invokeConstructor(contentProviderClass, new Object[0]));
		} catch (NoSuchMethodException e) {
			Activator.logUnexpected(null, e);
		} catch (IllegalAccessException e) {
			Activator.logUnexpected(null, e);
		} catch (InvocationTargetException e) {
			Activator.logUnexpected(null, e);
		} catch (InstantiationException e) {
			Activator.logUnexpected(null, e);
		}
	}

	@Override
	public void refresh() {
		if (!canvas.isDisposed()) {
			canvas.redraw();
		}
	}

	public void setAdjustToCanvas(boolean adjustToCanvas) {
		this.adjustToCanvas = adjustToCanvas;
		requestImageRedraw();
	}

	@Override
	public void setContentProvider(IContentProvider contentProvider) {
		if (contentProvider != null) {
			if (!(contentProvider instanceof IGraphicalContentProvider))
				throw new IllegalArgumentException(IGraphicalContentProvider.class.getName());
			if (adjustToCanvas && !canvas.isDisposed())
				((IGraphicalContentProvider) contentProvider).setSuggestedSize(canvas.getSize());
		}
		super.setContentProvider(contentProvider);
	}

	@Override
	public void setSelection(ISelection selection, boolean reveal) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Perform a zooming operation centered on the given point (dx, dy) and
	 * using the given scale factor. The given AffineTransform instance is
	 * preconcatenated.
	 * 
	 * @param dx
	 *            center x
	 * @param dy
	 *            center y
	 * @param scale
	 *            zoom rate
	 * @param af
	 *            original affinetransform
	 */
	public void centerZoom(double dx, double dy, double scale,
			AffineTransform af) {
		af.preConcatenate(AffineTransform.getTranslateInstance(-dx, -dy));
		af.preConcatenate(AffineTransform.getScaleInstance(scale, scale));
		af.preConcatenate(AffineTransform.getTranslateInstance(dx, dy));
		transform = af;
		syncScrollBars();
	}

	/**
	 * Zoom in around the center of client Area.
	 */
	public void zoomIn() {
		if (sourceImage == null)
			return;
		Rectangle rect = canvas.getClientArea();
		int w = rect.width, h = rect.height;
		double dx = ((double) w) / 2;
		double dy = ((double) h) / 2;
		centerZoom(dx, dy, ZOOMIN_RATE, transform);
	}

	/**
	 * Zoom out around the center of client Area.
	 */
	public void zoomOut() {
		if (sourceImage == null)
			return;
		Rectangle rect = canvas.getClientArea();
		int w = rect.width, h = rect.height;
		double dx = ((double) w) / 2;
		double dy = ((double) h) / 2;
		centerZoom(dx, dy, ZOOMOUT_RATE, transform);
	}
	
	/**
	 * Show the image with the original size
	 */
	public void showOriginal() {
		if (sourceImage == null)
			return;
		transform = new AffineTransform();
		syncScrollBars();
	}
	
	/**
	 * Fit the image onto the canvas
	 */
	public void fitCanvas() {
		if (sourceImage == null)
			return;
		Rectangle imageBound = sourceImage.getBounds();
		Rectangle destRect = canvas.getClientArea();
		double sx = (double) destRect.width / (double) imageBound.width;
		double sy = (double) destRect.height / (double) imageBound.height;
		double s = Math.min(sx, sy);
		double dx = 0.5 * destRect.width;
		double dy = 0.5 * destRect.height;
		centerZoom(dx, dy, s, new AffineTransform());
	}
	
	/**
	 * Get the image data. (for future use only)
	 * 
	 * @return image data of canvas
	 */
	public ImageData getImageData() {
		if (sourceImage == null)
			return null;

		return sourceImage.getImageData();
	}

	/**
	 * Reset the image data and update the image
	 * 
	 * @param data
	 *            image data to be set
	 */
	public void setImageData(ImageData data) {
		if (sourceImage != null)
			sourceImage.dispose();
		if (data != null)
			sourceImage = new Image(canvas.getDisplay(), data);
		syncScrollBars();
	}
	
	/**
	 * Dispose the garbage here
	 */
	public void dispose() {
		if (sourceImage != null && !sourceImage.isDisposed()) {
			sourceImage.dispose();
		}
		if (screenImage != null && !screenImage.isDisposed()) {
			screenImage.dispose();
		}
	}
}
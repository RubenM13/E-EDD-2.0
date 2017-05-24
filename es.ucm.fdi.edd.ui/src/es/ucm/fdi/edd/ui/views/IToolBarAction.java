package es.ucm.fdi.edd.ui.views;

import org.eclipse.swt.graphics.ImageData;

public interface IToolBarAction {

	public void zoomIn();

	public void zoomOut();

	public void fitCanvas();

	public void showOriginal();

	public void loadImage(String filename);

	public ImageData getImageData();

	public void setImageData(ImageData dest);
}

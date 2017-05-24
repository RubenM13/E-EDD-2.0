package es.ucm.fdi.edd.graphiti.diagram;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;
import org.eclipse.graphiti.ui.platform.IImageProvider;

public class EDDImageProvider extends AbstractImageProvider implements
		IImageProvider {
	
	protected static final String PREFIX = "es.ucm.fdi.edd.graphiti.providers.EDDImageProvider.";

	public static final String IMG_CREATE_NODE = PREFIX + "createNode";
	public static final String IMG_DELETE_NODE = PREFIX + "deleteNode";

	public EDDImageProvider() {
		super();
	}

	@Override
	protected void addAvailableImages() {
		addImageFilePath(IMG_CREATE_NODE, "icons/create_node.png");
		addImageFilePath(IMG_DELETE_NODE, "icons/delete_node.png");
	}
}
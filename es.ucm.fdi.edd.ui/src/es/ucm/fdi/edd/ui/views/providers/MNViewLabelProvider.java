package es.ucm.fdi.edd.ui.views.providers;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import es.ucm.fdi.edd.ui.Activator;
import es.ucm.fdi.emf.model.ed2.Leaf;
import es.ucm.fdi.emf.model.ed2.Node;
import es.ucm.fdi.emf.model.ed2.TreeElementType;

/**
 * A decorating label provider is a label provider which combines a nested label provider and an optional decorator.
 * The decorator decorates the label text, image, font and colors provided by the nested label provider.
 */
public class MNViewLabelProvider extends AdapterFactoryLabelProvider {
	
    /** Image descriptor for warning overlay. */
//	private static final ImageDescriptor WARNING_IMG = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.ui", "$nl$/icons/full/ovr16/warning_ovr.gif");
    
    private static final ImageDescriptor TREELEMENT_IMG1 = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "/icons/treeElement1.gif");
    private static final ImageDescriptor TREELEMENT_IMG2 = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "$nl$/icons/treeElement2.gif");
    private static final ImageDescriptor TREELEMENT_IMG3 = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "$nl$/icons/treeElement3.gif");
    private static final ImageDescriptor TREELEMENT_IMG4 = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "$nl$/icons/treeElement4.gif");
    private static final ImageDescriptor TREELEMENT_IMG5 = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "$nl$/icons/treeElement5.gif");

    /** The image decorator. */
//  private Image decoratedImage;
    
    private Image decoratedImage1;
    private Image decoratedImage2;
    private Image decoratedImage3;
    private Image decoratedImage4;
    private Image decoratedImage5;

	/**
	 * Creates a new instance of {@link MNViewLabelProvider}.
	 */
	public MNViewLabelProvider() {
		super(MNComposedAdapterFactory.getAdapterFactory());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object element) {
		Image image = super.getImage(element);
		if (element instanceof Node) {
			Node item = (Node) element;
            return getDecoratedImage(image, item.getType().getValue());
        } else if (element instanceof Leaf) {
			Leaf item = (Leaf) element;
            return getDecoratedImage(image, item.getType().getValue());
        }

        return image;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object element) {
		return super.getText(element);
	}
	
	private Image getDecoratedImage(Image image, int type) {
		switch (type) {
			case TreeElementType.EMPTY_VALUE:
				return image;
			case TreeElementType.YES_VALUE:
				if (decoratedImage1 == null) {
                    DecorationOverlayIcon icon = new DecorationOverlayIcon(image, TREELEMENT_IMG1, IDecoration.TOP_RIGHT);
                    decoratedImage1 = icon.createImage();
                }
                return decoratedImage1;
			case TreeElementType.NO_VALUE:
				if (decoratedImage2 == null) {
                    DecorationOverlayIcon icon = new DecorationOverlayIcon(image, TREELEMENT_IMG2, IDecoration.TOP_RIGHT);
                    decoratedImage2 = icon.createImage();
                }
                return decoratedImage2;
			case TreeElementType.TRUSTED_VALUE:
				if (decoratedImage3 == null) {
                    DecorationOverlayIcon icon = new DecorationOverlayIcon(image, TREELEMENT_IMG3, IDecoration.TOP_RIGHT);
                    decoratedImage3 = icon.createImage();
                }
                return decoratedImage3;
			case TreeElementType.DONT_KNOW_VALUE:
				if (decoratedImage4 == null) {
                    DecorationOverlayIcon icon = new DecorationOverlayIcon(image, TREELEMENT_IMG4, IDecoration.TOP_RIGHT);
                    decoratedImage4 = icon.createImage();
                }
                return decoratedImage4;
			case TreeElementType.INADMISSIBLE_VALUE:
				if (decoratedImage5 == null) {
                    DecorationOverlayIcon icon = new DecorationOverlayIcon(image, TREELEMENT_IMG5, IDecoration.TOP_RIGHT);
                    decoratedImage5 = icon.createImage();
                }
                return decoratedImage5;
				
			default:
				break;
		}
		
		return image;
	}
	
	/** {@inheritDoc} */
    @Override
    public void dispose() {
        super.dispose();
//      decoratedImage.dispose();
        if (decoratedImage1 != null)
        	decoratedImage1.dispose();
        if (decoratedImage2 != null)
        	decoratedImage2.dispose();
        if (decoratedImage3 != null)
        	decoratedImage3.dispose();
        if (decoratedImage4 != null)
        	decoratedImage4.dispose();
        if (decoratedImage5 != null)
        	decoratedImage5.dispose();
    }
}
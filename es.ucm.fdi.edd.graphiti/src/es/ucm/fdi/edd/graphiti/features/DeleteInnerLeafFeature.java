package es.ucm.fdi.edd.graphiti.features;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

import es.ucm.fdi.edd.emf.model.edd.Leaf;
import es.ucm.fdi.edd.emf.model.edd.TreeElement;

public class DeleteInnerLeafFeature extends AbstractCustomFeature {

	public DeleteInnerLeafFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getName() {
		return "Delete Leaf";
	}

	@Override
	public String getDescription() {
		return "Deletes the selected leaf inside this tree element";
	}

	@Override
	public boolean isAvailable(IContext context) {
		return getLeafDomainObject(context) != null;
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		return getLeafDomainObject(context) != null;
	}

	public void execute(ICustomContext context) {
		Leaf leaf = getLeafDomainObject(context);
		EcoreUtil.delete(leaf);
	}

	private Leaf getLeafDomainObject(IContext context) {
		if (context instanceof ICustomContext) {
			PictogramElement[] pictogramElements = ((ICustomContext) context).getPictogramElements();
			if (pictogramElements.length == 1) {
				PictogramElement pictogramElement = pictogramElements[0];
				Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
				if (domainObject instanceof Leaf) {
					if (pictogramElement instanceof Shape && pictogramElement.eContainer() instanceof ContainerShape
							&& pictogramElement.eContainer().eContainer() instanceof ContainerShape) {
						if (getBusinessObjectForPictogramElement((ContainerShape) pictogramElement.eContainer()
								.eContainer()) instanceof TreeElement) {
							return (Leaf) domainObject;
						}
					}
				}
			}
		}
		return null;
	}
}

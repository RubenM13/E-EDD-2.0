package es.ucm.fdi.edd.graphiti.features;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

import es.ucm.fdi.edd.emf.model.edd.Node;
import es.ucm.fdi.edd.emf.model.edd.TreeElement;

public class DeleteInnerNodeFeature extends AbstractCustomFeature {

	public DeleteInnerNodeFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getName() {
		return "Delete Node";
	}

	@Override
	public String getDescription() {
		return "Deletes the selected node inside this tree element";
	}

	@Override
	public boolean isAvailable(IContext context) {
		return getNodeDomainObject(context) != null;
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		return getNodeDomainObject(context) != null;
	}

	public void execute(ICustomContext context) {
		Node node = getNodeDomainObject(context);
		EcoreUtil.delete(node);
	}

	private Node getNodeDomainObject(IContext context) {
		if (context instanceof ICustomContext) {
			PictogramElement[] pictogramElements = ((ICustomContext) context).getPictogramElements();
			if (pictogramElements.length == 1) {
				PictogramElement pictogramElement = pictogramElements[0];
				Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
				if (domainObject instanceof Node) {
					if (pictogramElement instanceof Shape && pictogramElement.eContainer() instanceof ContainerShape
							&& pictogramElement.eContainer().eContainer() instanceof ContainerShape) {
						if (getBusinessObjectForPictogramElement((ContainerShape) pictogramElement.eContainer()
								.eContainer()) instanceof TreeElement) {
							return (Node) domainObject;
						}
					}
				}
			}
		}
		return null;
	}
}

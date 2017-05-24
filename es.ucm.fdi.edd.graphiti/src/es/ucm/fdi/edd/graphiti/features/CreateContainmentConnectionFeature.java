package es.ucm.fdi.edd.graphiti.features;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

import es.ucm.fdi.edd.emf.model.edd.EDD;
import es.ucm.fdi.edd.emf.model.edd.Leaf;
import es.ucm.fdi.edd.emf.model.edd.Node;
import es.ucm.fdi.edd.emf.model.edd.TreeElement;

public class CreateContainmentConnectionFeature extends AbstractCreateConnectionFeature implements
		ICreateConnectionFeature {

	public CreateContainmentConnectionFeature(IFeatureProvider fp) {
		super(fp, "Containment", "Creates a new containment relation between two folders");
	}

	public boolean canStartConnection(ICreateConnectionContext context) {
		// Defines the start of the connection; allowed are objects that may
		// contain other objects
		Object domainObject = getBusinessObjectForPictogramElement(context.getSourcePictogramElement());
		return domainObject instanceof TreeElement || domainObject instanceof EDD;
	}

	public boolean canCreate(ICreateConnectionContext context) {
		PictogramElement sourcePictogramElement = context.getSourcePictogramElement();
		PictogramElement targetPictogramElement = context.getTargetPictogramElement();

		if (sourcePictogramElement == null || targetPictogramElement == null) {
			return false;
		}

		Object sourceDomainObject = getBusinessObjectForPictogramElement(sourcePictogramElement);
		Object targetDomainObject = getBusinessObjectForPictogramElement(targetPictogramElement);

		return (sourceDomainObject instanceof TreeElement || sourceDomainObject instanceof EDD)
				&& (targetDomainObject instanceof TreeElement || targetDomainObject instanceof Leaf);
	}

	public Connection create(ICreateConnectionContext context) {
		Anchor sourceAnchor = context.getSourceAnchor();
		Anchor targetAnchor = context.getTargetAnchor();

		if (targetAnchor == null) {
			// Target destination is somewhere inside structured folder
			// representation
			Shape shape = (Shape) context.getTargetPictogramElement();
			while (shape.getAnchors().isEmpty()) {
				shape = shape.getContainer();
			}
			targetAnchor = shape.getAnchors().get(0);
		}

		EObject sourceObject = (EObject) getBusinessObjectForPictogramElement(sourceAnchor.getParent());
		EObject targetObject = (EObject) getBusinessObjectForPictogramElement(targetAnchor.getParent());

		if (sourceObject instanceof EDD) {
			if (targetObject instanceof TreeElement) {
				((EDD) sourceObject).getElements().add((TreeElement) targetObject);
			} else {
				throw new IllegalStateException("EDD may only contain TreeElements");
			}
		} else if (sourceObject instanceof TreeElement) {
			/*if (targetObject instanceof TreeElement) {
				((TreeElement) sourceObject).getFolders().add((TreeElement) targetObject);
			} else if (targetObject instanceof Leaf) {
				((TreeElement) sourceObject).getFiles().add((Leaf) targetObject);
			} else {
				throw new IllegalStateException("TreeElement may only contain Folders or Files");
			}*/
		} else if (sourceObject instanceof Node) {
			if (targetObject instanceof Node) {
				((Node) sourceObject).getChildren().add((TreeElement) targetObject);
			} else {
				throw new IllegalStateException("Node may only contain TreeElements");
			}
		} else if (sourceObject instanceof Leaf) {
			if (targetObject instanceof Leaf) {
				
			} else {
				throw new IllegalStateException("Leaf may only contain ...");
			}
		} else {
			throw new IllegalStateException("Unknown container object");
		}

		AddConnectionContext addContext = new AddConnectionContext(sourceAnchor, targetAnchor);
		getFeatureProvider().addIfPossible(addContext);

		return null;
	}
}

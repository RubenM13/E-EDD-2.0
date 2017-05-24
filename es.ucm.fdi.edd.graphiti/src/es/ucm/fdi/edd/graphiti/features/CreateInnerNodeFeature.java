package es.ucm.fdi.edd.graphiti.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import es.ucm.fdi.edd.emf.model.edd.EddFactory;
import es.ucm.fdi.edd.emf.model.edd.Node;
import es.ucm.fdi.edd.emf.model.edd.TreeElement;

public class CreateInnerNodeFeature extends AbstractCustomFeature {

	public CreateInnerNodeFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getName() {
		return "Create Node";
	}

	@Override
	public String getDescription() {
		return "Creates a new node inside this tree element";
	}

	@Override
	public boolean isAvailable(IContext context) {
		return getTreeElementDomainObject(context) != null;
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		return getTreeElementDomainObject(context) != null;
	}

	public void execute(ICustomContext context) {
		TreeElement treeElement = getTreeElementDomainObject(context);
		String newName = createNewName(treeElement);
		Node node = EddFactory.eINSTANCE.createNode();
		node.setName(newName);
		treeElement.eResource().getContents().add(node);
		//FIME treeElement.getFiles().add(node);
	}

	private TreeElement getTreeElementDomainObject(IContext context) {
		if (context instanceof ICustomContext) {
			PictogramElement[] pictogramElements = ((ICustomContext) context).getPictogramElements();
			if (pictogramElements.length == 1) {
				PictogramElement pictogramElement = pictogramElements[0];
				Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
				if (domainObject instanceof TreeElement) {
					return (TreeElement) domainObject;
				}
			}
		}
		return null;
	}

	private String createNewName(TreeElement treeElement) {
		String initialName = "NewFile";
		String name = initialName;
		int number = 0;
		while (findFile(treeElement, name) != null) {
			number++;
			name = initialName + number;
		}
		return name;
	}

	private Node findFile(TreeElement treeElement, String name) {
		//FIXME 
//		for (Node node : treeElement.getFiles()) {
//			if (name.equals(node.getName())) {
//				return node;
//			}
//		}
		return null;
	}
}

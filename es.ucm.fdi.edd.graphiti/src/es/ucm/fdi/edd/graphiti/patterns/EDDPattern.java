package es.ucm.fdi.edd.graphiti.patterns;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.pattern.AbstractPattern;
import org.eclipse.graphiti.pattern.IPattern;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

import es.ucm.fdi.edd.emf.model.edd.EDD;
import es.ucm.fdi.edd.emf.model.edd.EddFactory;
import es.ucm.fdi.edd.graphiti.ui.EDDPredefinedColoredAreas;

public class EDDPattern extends AbstractPattern implements IPattern {

	public EDDPattern() {
		super(null);
	}

	@Override
	public String getCreateName() {
		return "EDD";
	}

	@Override
	public boolean isMainBusinessObjectApplicable(Object mainBusinessObject) {
		return mainBusinessObject instanceof EDD;
	}

	@Override
	protected boolean isPatternControlled(PictogramElement pictogramElement) {
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
		return isMainBusinessObjectApplicable(domainObject);
	}

	@Override
	protected boolean isPatternRoot(PictogramElement pictogramElement) {
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
		return isMainBusinessObjectApplicable(domainObject);
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		EDD newEDD = EddFactory.eINSTANCE.createEDD();
		newEDD.setName(createNewName());

		getDiagram().eResource().getContents().add(newEDD);

		addGraphicalRepresentation(context, newEDD);
		return new Object[] { newEDD };
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return context.getNewObject() instanceof EDD && context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		Diagram targetDiagram = (Diagram) context.getTargetContainer();
		EDD addedDomainObject = (EDD) context.getNewObject();
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();

		// Outer container (invisible)
		ContainerShape outerContainerShape = peCreateService.createContainerShape(targetDiagram, true);
		Rectangle outerRectangle = gaService.createInvisibleRectangle(outerContainerShape);
		gaService.setLocationAndSize(outerRectangle, context.getX(), context.getY(), context.getWidth(),
				context.getHeight());

		// Register tab
		Rectangle registerRectangle = gaService.createRectangle(outerRectangle);
		gaService.setLocationAndSize(registerRectangle, 0, 0, 20, 20);
		registerRectangle.setFilled(true);
		gaService.setRenderingStyle(registerRectangle, EDDPredefinedColoredAreas.getLightGrayAdaptions());

		// Main contents area
		Rectangle mainRectangle = gaService.createRectangle(outerRectangle);
		setLocationAndSizeOfMainContentsArea(outerRectangle, mainRectangle);
		mainRectangle.setFilled(true);
		gaService.setRenderingStyle(mainRectangle, EDDPredefinedColoredAreas.getLightGrayAdaptions());

		// Folder name
		Shape shape = peCreateService.createShape(outerContainerShape, false);
		Text text = gaService.createText(shape, addedDomainObject.getName());
		text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
		setLocationAndSizeOfTextArea(outerRectangle, text);

		peCreateService.createChopboxAnchor(outerContainerShape);

		link(outerContainerShape, addedDomainObject);

		return outerContainerShape;
	}

	@Override
	public boolean canLayout(ILayoutContext context) {
		return context.getPictogramElement() instanceof ContainerShape
				&& getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof EDD;
	}

	@Override
	public boolean layout(ILayoutContext context) {
		boolean changesDone = false;
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof ContainerShape) {
			ContainerShape outerContainerShape = (ContainerShape) pictogramElement;
			GraphicsAlgorithm outerGraphicsAlgorithm = outerContainerShape.getGraphicsAlgorithm();
			if (outerGraphicsAlgorithm instanceof Rectangle) {
				Rectangle outerRectangle = (Rectangle) outerGraphicsAlgorithm;

				// Adapt size of main contents area
				EList<GraphicsAlgorithm> graphicsAlgorithmChildren = outerRectangle.getGraphicsAlgorithmChildren();
				if (graphicsAlgorithmChildren.size() > 1) {
					GraphicsAlgorithm graphicsAlgorithm = graphicsAlgorithmChildren.get(1);
					if (graphicsAlgorithm instanceof Rectangle) {
						setLocationAndSizeOfMainContentsArea(outerRectangle, (Rectangle) graphicsAlgorithm);
						changesDone = true;
					}
				}
			}
		}

		// Adapt size and location of text field
		Rectangle outerRectangle = getOuterRectangle(pictogramElement);
		Text nameText = getNameText(pictogramElement);
		if (outerRectangle != null && nameText != null) {
			setLocationAndSizeOfTextArea(outerRectangle, nameText);
			changesDone = true;
		}

		return changesDone;
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		Text nameText = getNameText(context.getPictogramElement());
		EDD domainObject = (EDD) getBusinessObjectForPictogramElement(context.getPictogramElement());
		if (domainObject.getName() == null || !domainObject.getName().equals(nameText.getValue())) {
			return Reason.createTrueReason("Name differs. Expected: '" + domainObject.getName() + "'");
		}
		return Reason.createFalseReason();
	}

	@Override
	public boolean update(IUpdateContext context) {
		Text nameText = getNameText(context.getPictogramElement());
		EDD domainObject = (EDD) getBusinessObjectForPictogramElement(context.getPictogramElement());
		nameText.setValue(domainObject.getName());
		return true;
	}

	@Override
	public int getEditingType() {
		return TYPE_TEXT;
	}

	@Override
	public boolean canDirectEdit(IDirectEditingContext context) {
		Object domainObject = getBusinessObjectForPictogramElement(context.getPictogramElement());
		GraphicsAlgorithm ga = context.getGraphicsAlgorithm();
		if (domainObject instanceof EDD && ga instanceof Text) {
			return true;
		}
		return false;
	}

	@Override
	public String getInitialValue(IDirectEditingContext context) {
		EDD edd = (EDD) getBusinessObjectForPictogramElement(context.getPictogramElement());
		return edd.getName();
	}

	@Override
	public String checkValueValid(String value, IDirectEditingContext context) {
		if (value == null || value.length() == 0) {
			return "Folder name must not be empty";
		}

		EDD edd = (EDD) getBusinessObjectForPictogramElement(context.getPictogramElement());
		EList<Shape> children = getDiagram().getChildren();
		for (Shape child : children) {
			Object domainObject = getBusinessObjectForPictogramElement(child);
			if (domainObject instanceof EDD) {
				if (!domainObject.equals(edd) && value.equals(((EDD) domainObject).getName())) {
					return "A edd with name '" + ((EDD) domainObject).getName() + "' already exists.";
				}
			}
		}
		return null;
	}

	@Override
	public void setValue(String value, IDirectEditingContext context) {
		EDD edd = (EDD) getBusinessObjectForPictogramElement(context.getPictogramElement());
		edd.setName(value);
		updatePictogramElement(context.getPictogramElement());
	}

	private void setLocationAndSizeOfMainContentsArea(Rectangle outerRectangle, Rectangle mainRectangle) {
		Graphiti.getGaService().setLocationAndSize(mainRectangle, 0, 10, outerRectangle.getWidth(),
				outerRectangle.getHeight() - 10);
	}

	private void setLocationAndSizeOfTextArea(Rectangle outerRectangle, Text text) {
		Graphiti.getGaService().setLocationAndSize(text, 0, 10, outerRectangle.getWidth(),
				outerRectangle.getHeight() - 10);
	}

	private Rectangle getOuterRectangle(PictogramElement pictogramElement) {
		if (pictogramElement instanceof ContainerShape) {
			ContainerShape outerContainerShape = (ContainerShape) pictogramElement;
			GraphicsAlgorithm outerGraphicsAlgorithm = outerContainerShape.getGraphicsAlgorithm();
			if (outerGraphicsAlgorithm instanceof Rectangle) {
				return (Rectangle) outerGraphicsAlgorithm;
			}
		}
		return null;
	}

	private Text getNameText(PictogramElement pictogramElement) {
		if (pictogramElement instanceof ContainerShape) {
			ContainerShape outerContainerShape = (ContainerShape) pictogramElement;
			GraphicsAlgorithm outerGraphicsAlgorithm = outerContainerShape.getGraphicsAlgorithm();
			if (outerGraphicsAlgorithm instanceof Rectangle) {
				EList<Shape> children = outerContainerShape.getChildren();
				if (children.size() > 0) {
					Shape shape = children.get(0);
					GraphicsAlgorithm graphicsAlgorithm = shape.getGraphicsAlgorithm();
					if (graphicsAlgorithm instanceof Text) {
						return (Text) graphicsAlgorithm;
					}
				}
			}
		}
		return null;
	}

	private String createNewName() {
		String initialName = "NewEDD";
		String name = initialName;
		int number = 0;
		while (findEDD(name) != null) {
			number++;
			name = initialName + number;
		}
		return name;
	}

	private EDD findEDD(String name) {
		EList<EObject> contents = getDiagram().eResource().getContents();
		for (EObject eObject : contents) {
			if (eObject instanceof EDD) {
				if (name.equals(((EDD) eObject).getName())) {
					return (EDD) eObject;
				}
			}
		}
		return null;
	}
}

package es.ucm.fdi.edd.graphiti.patterns;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.pattern.IPattern;
import org.eclipse.graphiti.pattern.id.IdLayoutContext;
import org.eclipse.graphiti.pattern.id.IdPattern;
import org.eclipse.graphiti.pattern.id.IdUpdateContext;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

import es.ucm.fdi.edd.emf.model.edd.EddFactory;
import es.ucm.fdi.edd.emf.model.edd.Node;
import es.ucm.fdi.edd.graphiti.ui.EDDPredefinedColoredAreas;

public class NodePattern extends IdPattern implements IPattern {

	private static final String ID_NAME_TEXT = "nameText";
	private static final String ID_OUTER_RECTANGLE = "outerRectangle";
	private static final String ID_MAIN_RECTANGLE = "mainRectangle";

	public NodePattern() {
		super();
	}

	@Override
	public String getCreateName() {
		return "Node";
	}

	@Override
	public boolean isMainBusinessObjectApplicable(Object mainBusinessObject) {
		return mainBusinessObject instanceof Node;
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		Node newNode = EddFactory.eINSTANCE.createNode();
		newNode.setName(createNewName());

		getDiagram().eResource().getContents().add(newNode);

		addGraphicalRepresentation(context, newNode);
		return new Object[] { newNode };
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return super.canAdd(context) && context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public PictogramElement doAdd(IAddContext context) {
		Diagram targetDiagram = (Diagram) context.getTargetContainer();
		Node addedDomainObject = (Node) context.getNewObject();
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();

		// Outer container (invisible)
		ContainerShape outerContainerShape = peCreateService.createContainerShape(targetDiagram, true);
		Rectangle outerRectangle = gaService.createInvisibleRectangle(outerContainerShape);
		setId(outerRectangle, ID_OUTER_RECTANGLE);
		gaService.setLocationAndSize(outerRectangle, context.getX(), context.getY(), context.getWidth(),
				context.getHeight());

		// Register tab
		RoundedRectangle registerRectangle = gaService.createRoundedRectangle(outerRectangle, 5, 5);
		gaService.setLocationAndSize(registerRectangle, 0, 0, 20, 20);
		registerRectangle.setFilled(true);
		gaService.setRenderingStyle(registerRectangle, EDDPredefinedColoredAreas.getGreenWhiteAdaptions());

		// Main contents area
		Rectangle mainRectangle = gaService.createRectangle(outerRectangle);
		setId(mainRectangle, ID_MAIN_RECTANGLE);
		mainRectangle.setFilled(true);
		gaService.setRenderingStyle(mainRectangle, EDDPredefinedColoredAreas.getGreenWhiteAdaptions());

		// Node name
		Shape shape = peCreateService.createShape(outerContainerShape, false);
		Text text = gaService.createText(shape, addedDomainObject.getName());
		setId(text, ID_NAME_TEXT);
		text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);

		peCreateService.createChopboxAnchor(outerContainerShape);

		link(outerContainerShape, addedDomainObject);
		link(shape, addedDomainObject);

		return outerContainerShape;
	}

	@Override
	protected boolean layout(IdLayoutContext context, String id) {
		boolean changesDone = false;

		Rectangle outerRectangle = (Rectangle) context.getRootPictogramElement().getGraphicsAlgorithm();

		if (id.equals(ID_MAIN_RECTANGLE) || id.equals(ID_NAME_TEXT)) {
			GraphicsAlgorithm ga = context.getGraphicsAlgorithm();
			Graphiti.getGaService().setLocationAndSize(ga, 0, 10, outerRectangle.getWidth(),
					outerRectangle.getHeight() - 10);
			changesDone = true;
		}

		return changesDone;
	}

	@Override
	protected IReason updateNeeded(IdUpdateContext context, String id) {
		if (id.equals(ID_NAME_TEXT)) {
			Text nameText = (Text) context.getGraphicsAlgorithm();
			Node domainObject = (Node) context.getDomainObject();
			if (domainObject.getName() == null || !domainObject.getName().equals(nameText.getValue())) {
				return Reason.createTrueReason("Name differs. Expected: '" + domainObject.getName() + "'");
			}
		}

		return Reason.createFalseReason();
	}

	@Override
	protected boolean update(IdUpdateContext context, String id) {
		if (id.equals(ID_NAME_TEXT)) {
			Text nameText = (Text) context.getGraphicsAlgorithm();
			Node domainObject = (Node) context.getDomainObject();
			nameText.setValue(domainObject.getName());
			return true;
		}
		return false;
	}

	@Override
	public int getEditingType() {
		return TYPE_TEXT;
	}

	@Override
	public boolean canDirectEdit(IDirectEditingContext context) {
		Object domainObject = getBusinessObjectForPictogramElement(context.getPictogramElement());
		GraphicsAlgorithm ga = context.getGraphicsAlgorithm();
		if (domainObject instanceof Node && ga instanceof Text) {
			return true;
		}
		return false;
	}

	@Override
	public String getInitialValue(IDirectEditingContext context) {
		Node node = (Node) getBusinessObjectForPictogramElement(context.getPictogramElement());
		return node.getName();
	}

	@Override
	public String checkValueValid(String value, IDirectEditingContext context) {
		if (value == null || value.length() == 0) {
			return "Node name must not be empty";
		}

		Node node = (Node) getBusinessObjectForPictogramElement(context.getPictogramElement());
		EList<Shape> children = getDiagram().getChildren();
		for (Shape child : children) {
			Object domainObject = getBusinessObjectForPictogramElement(child);
			if (domainObject instanceof Node) {
				if (!domainObject.equals(node) && value.equals(((Node) domainObject).getName())) {
					return "A node with name '" + ((Node) domainObject).getName() + "' already exists.";
				}
			}
		}
		return null;
	}

	@Override
	public void setValue(String value, IDirectEditingContext context) {
		Node node = (Node) getBusinessObjectForPictogramElement(context.getPictogramElement());
		node.setName(value);
		updatePictogramElement(context.getPictogramElement());
	}

	private String createNewName() {
		String initialName = "NewNode";
		String name = initialName;
		int number = 0;
		while (findNode(name) != null) {
			number++;
			name = initialName + number;
		}
		return name;
	}

	private Node findNode(String name) {
		EList<EObject> contents = getDiagram().eResource().getContents();
		for (EObject eObject : contents) {
			if (eObject instanceof Node) {
				if (name.equals(((Node) eObject).getName())) {
					return (Node) eObject;
				}
			}
		}
		return null;
	}
}

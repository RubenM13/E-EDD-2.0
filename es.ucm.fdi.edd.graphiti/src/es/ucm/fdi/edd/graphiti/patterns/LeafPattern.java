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
import es.ucm.fdi.edd.emf.model.edd.Leaf;
import es.ucm.fdi.edd.graphiti.ui.EDDPredefinedColoredAreas;

public class LeafPattern extends IdPattern implements IPattern {

	private static final String ID_NAME_TEXT = "nameText";
	private static final String ID_OUTER_RECTANGLE = "outerRectangle";
	private static final String ID_MAIN_RECTANGLE = "mainRectangle";

	public LeafPattern() {
		super();
	}

	@Override
	public String getCreateName() {
		return "Leaf";
	}

	@Override
	public boolean isMainBusinessObjectApplicable(Object mainBusinessObject) {
		return mainBusinessObject instanceof Leaf;
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		Leaf newLeaf = EddFactory.eINSTANCE.createLeaf();
		newLeaf.setName(createNewName());

		getDiagram().eResource().getContents().add(newLeaf);

		addGraphicalRepresentation(context, newLeaf);
		return new Object[] { newLeaf };
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return super.canAdd(context) && context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public PictogramElement doAdd(IAddContext context) {
		Diagram targetDiagram = (Diagram) context.getTargetContainer();
		Leaf addedDomainObject = (Leaf) context.getNewObject();
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

		// Leaf name
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
			Leaf domainObject = (Leaf) context.getDomainObject();
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
			Leaf domainObject = (Leaf) context.getDomainObject();
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
		if (domainObject instanceof Leaf && ga instanceof Text) {
			return true;
		}
		return false;
	}

	@Override
	public String getInitialValue(IDirectEditingContext context) {
		Leaf leaf = (Leaf) getBusinessObjectForPictogramElement(context.getPictogramElement());
		return leaf.getName();
	}

	@Override
	public String checkValueValid(String value, IDirectEditingContext context) {
		if (value == null || value.length() == 0) {
			return "Leaf name must not be empty";
		}

		Leaf leaf = (Leaf) getBusinessObjectForPictogramElement(context.getPictogramElement());
		EList<Shape> children = getDiagram().getChildren();
		for (Shape child : children) {
			Object domainObject = getBusinessObjectForPictogramElement(child);
			if (domainObject instanceof Leaf) {
				if (!domainObject.equals(leaf) && value.equals(((Leaf) domainObject).getName())) {
					return "A leaf with name '" + ((Leaf) domainObject).getName() + "' already exists.";
				}
			}
		}
		return null;
	}

	@Override
	public void setValue(String value, IDirectEditingContext context) {
		Leaf leaf = (Leaf) getBusinessObjectForPictogramElement(context.getPictogramElement());
		leaf.setName(value);
		updatePictogramElement(context.getPictogramElement());
	}

	private String createNewName() {
		String initialName = "NewLeaf";
		String name = initialName;
		int number = 0;
		while (findLeaf(name) != null) {
			number++;
			name = initialName + number;
		}
		return name;
	}

	private Leaf findLeaf(String name) {
		EList<EObject> contents = getDiagram().eResource().getContents();
		for (EObject eObject : contents) {
			if (eObject instanceof Leaf) {
				if (name.equals(((Leaf) eObject).getName())) {
					return (Leaf) eObject;
				}
			}
		}
		return null;
	}
}

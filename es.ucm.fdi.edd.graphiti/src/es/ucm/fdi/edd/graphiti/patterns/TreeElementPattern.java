package es.ucm.fdi.edd.graphiti.patterns;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
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
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.graphiti.util.PredefinedColoredAreas;

import es.ucm.fdi.edd.emf.model.edd.EddFactory;
import es.ucm.fdi.edd.emf.model.edd.Node;
import es.ucm.fdi.edd.emf.model.edd.TreeElement;

public class TreeElementPattern extends IdPattern implements IPattern {

	private static final String ID_TREEELEMENT_NAME_TEXT = "treeElementNameText";
	private static final String ID_OUTER_RECTANGLE = "outerRectangle";
	private static final String ID_MAIN_RECTANGLE = "mainRectangle";
	private static final String ID_NAME_SEPARATOR = "nameSeparator";
	private static final String ID_NODE_NAMES_RECTANGLE = "nodeNamesRectangle";
	private static final String ID_NODE_NAME_TEXT = "nodeNameText";

	public TreeElementPattern() {
		super();
	}

	@Override
	public String getCreateName() {
		return "TreeElement";
	}

	@Override
	public boolean isMainBusinessObjectApplicable(Object mainBusinessObject) {
		return mainBusinessObject instanceof TreeElement;
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		TreeElement newTreeElement = EddFactory.eINSTANCE.createTreeElement();
		getDiagram().eResource().getContents().add(newTreeElement);
		newTreeElement.setName(createNewName());

		addGraphicalRepresentation(context, newTreeElement);
		return new Object[] { newTreeElement };
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return super.canAdd(context) && context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public PictogramElement doAdd(IAddContext context) {
		Diagram targetDiagram = (Diagram) context.getTargetContainer();
		TreeElement addedTreeElement = (TreeElement) context.getNewObject();
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();

		// Outer container (invisible)
		ContainerShape outerContainerShape = peCreateService.createContainerShape(targetDiagram, true);
		Rectangle outerRectangle = gaService.createInvisibleRectangle(outerContainerShape);
		setId(outerRectangle, ID_OUTER_RECTANGLE);
		gaService.setLocationAndSize(outerRectangle, context.getX(), context.getY(), context.getWidth(),
				context.getHeight());

		// Register tab
		Rectangle registerRectangle = gaService.createRectangle(outerRectangle);
		gaService.setLocationAndSize(registerRectangle, 0, 0, 20, 20);
		registerRectangle.setFilled(true);
		gaService.setRenderingStyle(registerRectangle, PredefinedColoredAreas.getSilverWhiteGlossAdaptions());

		// Main contents area
		Rectangle mainRectangle = gaService.createRectangle(outerRectangle);
		setId(mainRectangle, ID_MAIN_RECTANGLE);
		mainRectangle.setFilled(true);
		gaService.setRenderingStyle(mainRectangle, PredefinedColoredAreas.getSilverWhiteGlossAdaptions());

		// TreeElement name
		Shape textShape = peCreateService.createShape(outerContainerShape, false);
		Text treeElementNameText = gaService.createText(textShape, "");
		setId(treeElementNameText, ID_TREEELEMENT_NAME_TEXT);
		treeElementNameText.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		treeElementNameText.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);

		// Separating line
		Shape lineShape = peCreateService.createShape(outerContainerShape, false);
		Polyline polyline = gaService.createPolyline(lineShape);
		setId(polyline, ID_NAME_SEPARATOR);
		polyline.setForeground(manageColor(IColorConstant.BLACK));

		// List of files in treeElement
		ContainerShape filesContainerShape = peCreateService.createContainerShape(outerContainerShape, false);
		Rectangle filesRectangle = gaService.createInvisibleRectangle(filesContainerShape);
		setId(filesRectangle, ID_NODE_NAMES_RECTANGLE);

		peCreateService.createChopboxAnchor(outerContainerShape);

		link(outerContainerShape, addedTreeElement);
		link(textShape, addedTreeElement);
		link(filesContainerShape, addedTreeElement);

		return outerContainerShape;
	}

	@Override
	protected boolean layout(IdLayoutContext context, String id) {
		boolean changesDone = false;

		Rectangle outerRectangle = (Rectangle) context.getRootPictogramElement().getGraphicsAlgorithm();

		GraphicsAlgorithm ga = context.getGraphicsAlgorithm();
		if (id.equals(ID_MAIN_RECTANGLE)) {
			Graphiti.getGaService().setLocationAndSize(ga, 0, 10, outerRectangle.getWidth(),
					outerRectangle.getHeight() - 10);
			changesDone = true;
		} else if (id.equals(ID_TREEELEMENT_NAME_TEXT)) {
			Graphiti.getGaService().setLocationAndSize(ga, 0, 10, outerRectangle.getWidth(), 20);
			changesDone = true;
		} else if (id.equals(ID_NAME_SEPARATOR)) {
			Polyline polyline = (Polyline) ga;
			polyline.getPoints().clear();
			List<Point> pointList = Graphiti.getGaService().createPointList(
					new int[] { 0, 30, outerRectangle.getWidth(), 30 });
			polyline.getPoints().addAll(pointList);
			changesDone = true;
		} else if (id.equals(ID_NODE_NAMES_RECTANGLE)) {
			Graphiti.getGaService().setLocationAndSize(ga, 0, 30, outerRectangle.getWidth(),
					outerRectangle.getHeight() - 30);
			changesDone = true;
		} else if (id.equals(ID_NODE_NAME_TEXT)) {
			int index = getIndex(context.getGraphicsAlgorithm());
			Graphiti.getGaService().setLocationAndSize(ga, 5, 30 + 20 * index, outerRectangle.getWidth() - 10, 20);
			changesDone = true;
		}

		return changesDone;
	}

	@Override
	protected IReason updateNeeded(IdUpdateContext context, String id) {
		if (id.equals(ID_TREEELEMENT_NAME_TEXT)) {
			Text nameText = (Text) context.getGraphicsAlgorithm();
			TreeElement domainObject = (TreeElement) context.getDomainObject();
			if (domainObject.getName() == null || !domainObject.getName().equals(nameText.getValue())) {
				return Reason.createTrueReason("Name differs. Expected: '" + domainObject.getName() + "'");
			}
		} else if (id.equals(ID_NODE_NAMES_RECTANGLE)) {
			ContainerShape filesContainerShape = (ContainerShape) context.getPictogramElement();
			TreeElement treeElement = (TreeElement) context.getDomainObject();
			//FIXME 
//			if (filesContainerShape.getChildren().size() != treeElement.getFiles().size()) {
//				return Reason.createTrueReason("Number of files differ. Expected: " + treeElement.getFiles().size());
//			}
		} else if (id.equals(ID_NODE_NAME_TEXT)) {
			Text nameText = (Text) context.getGraphicsAlgorithm();
			Node file = (Node) context.getDomainObject();
			if (file.getName() == null || !file.getName().equals(nameText.getValue())) {
				return Reason.createTrueReason("Name differs. Expected: '" + file.getName() + "'");
			}
		}

		return Reason.createFalseReason();
	}

	@Override
	protected boolean update(IdUpdateContext context, String id) {
		if (id.equals(ID_TREEELEMENT_NAME_TEXT)) {
			Text nameText = (Text) context.getGraphicsAlgorithm();
			TreeElement domainObject = (TreeElement) context.getDomainObject();
			nameText.setValue(domainObject.getName());
			return true;
		} else if (id.equals(ID_NODE_NAMES_RECTANGLE)) {
			EList<Shape> children = ((ContainerShape) context.getPictogramElement()).getChildren();
			Shape[] toDelete = children.toArray(new Shape[children.size()]);
			for (Shape shape : toDelete) {
				EcoreUtil.delete(shape, true);
			}
			//FIXME 
//			EList<File> files = ((TreeElement) context.getDomainObject()).getFiles();
//			int index = 0;
//			for (File file : files) {
//				Shape shape = Graphiti.getPeCreateService().createShape((ContainerShape) context.getPictogramElement(),
//						true);
//				Text fileNameText = Graphiti.getGaService().createText(shape, file.getName());
//				setId(fileNameText, ID_NODE_NAME_TEXT);
//				setIndex(fileNameText, index);
//				index++;
//				fileNameText.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
//				fileNameText.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
//				link(shape, file);
//			}
			return true;
		} else if (id.equals(ID_NODE_NAME_TEXT)) {
			Text nameText = (Text) context.getGraphicsAlgorithm();
			Node file = (Node) context.getDomainObject();
			nameText.setValue(file.getName());
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
		if (domainObject instanceof TreeElement && ga instanceof Text) {
			return true;
		}
		return false;
	}

	@Override
	public String getInitialValue(IDirectEditingContext context) {
		TreeElement treeElement = (TreeElement) getBusinessObjectForPictogramElement(context.getPictogramElement());
		return treeElement.getName();
	}

	@Override
	public String checkValueValid(String value, IDirectEditingContext context) {
		if (value == null || value.length() == 0) {
			return "TreeElement name must not be empty";
		}

		TreeElement treeElement = (TreeElement) getBusinessObjectForPictogramElement(context.getPictogramElement());
		EList<Shape> children = getDiagram().getChildren();
		for (Shape child : children) {
			Object domainObject = getBusinessObjectForPictogramElement(child);
			if (domainObject instanceof TreeElement) {
				if (!domainObject.equals(treeElement) && value.equals(((TreeElement) domainObject).getName())) {
					return "A treeElement with name '" + ((TreeElement) domainObject).getName() + "' already exists.";
				}
			}
		}
		return null;
	}

	@Override
	public void setValue(String value, IDirectEditingContext context) {
		TreeElement treeElement = (TreeElement) getBusinessObjectForPictogramElement(context.getPictogramElement());
		treeElement.setName(value);
		updatePictogramElement(context.getPictogramElement());
	}

	private String createNewName() {
		String initialName = "NewTreeElement";
		String name = initialName;
		int number = 0;
		while (findTreeElement(name) != null) {
			number++;
			name = initialName + number;
		}
		return name;
	}

	private TreeElement findTreeElement(String name) {
		EList<EObject> contents = getDiagram().eResource().getContents();
		for (EObject eObject : contents) {
			if (eObject instanceof TreeElement) {
				if (name.equals(((TreeElement) eObject).getName())) {
					return (TreeElement) eObject;
				}
			}
		}
		return null;
	}
}

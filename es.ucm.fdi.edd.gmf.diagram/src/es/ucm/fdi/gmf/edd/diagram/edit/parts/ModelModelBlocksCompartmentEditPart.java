package es.ucm.fdi.gmf.edd.diagram.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ListCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeConnectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.edit.policies.reparent.CreationEditPolicyWithCustomReparent;

import es.ucm.fdi.gmf.edd.diagram.edit.policies.ModelModelBlocksCompartmentCanonicalEditPolicy;
import es.ucm.fdi.gmf.edd.diagram.edit.policies.ModelModelBlocksCompartmentItemSemanticEditPolicy;
import es.ucm.fdi.gmf.edd.diagram.part.EddVisualIDRegistry;
import es.ucm.fdi.gmf.edd.diagram.part.Messages;
import es.ucm.fdi.gmf.edd.diagram.providers.EddElementTypes;

/**
 * @generated
 */
public class ModelModelBlocksCompartmentEditPart extends
		ListCompartmentEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 7001;

	/**
	 * @generated
	 */
	public ModelModelBlocksCompartmentEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected boolean hasModelChildrenChanged(Notification evt) {
		return false;
	}

	/**
	 * @generated
	 */
	public String getCompartmentName() {
		return Messages.ModelModelBlocksCompartmentEditPart_title;
	}

	/**
	 * @generated NOT
	 */
	public IFigure createFigure() {
		// FIXME Joel	
		ResizableCompartmentFigure result = (ResizableCompartmentFigure) super
				.createFigure();
		result.setTitleVisibility(false);

		// Fix the margins
		Insets insets = result.getContentPane().getInsets();
		insets.top = 5;
		insets.bottom = 5;
		insets.left = 5;
		insets.right = 5;
		// Layout behavior modification : data must be represented horizontally
		ConstrainedToolbarLayout layoutMgr = (ConstrainedToolbarLayout) result
				.getContentPane().getLayoutManager();
		layoutMgr.setHorizontal(true);
		layoutMgr.setStretchMajorAxis(true); // uses all the avalaible space
		layoutMgr.setStretchMinorAxis(true); // uses all the avalaible space
		layoutMgr.setSpacing(5);

		//		FreeFormLayoutEx layoutMgr = (FreeFormLayoutEx) result.getContentPane().getLayoutManager();

		return result;
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ModelModelBlocksCompartmentItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new CreationEditPolicyWithCustomReparent(
						EddVisualIDRegistry.TYPED_INSTANCE));
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
				new DragDropEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new ModelModelBlocksCompartmentCanonicalEditPolicy());
	}

	/**
	 * @generated
	 */
	protected void setRatio(Double ratio) {
		if (getFigure().getParent().getLayoutManager() instanceof ConstrainedToolbarLayout) {
			super.setRatio(ratio);
		}
	}

	/**
	 * @generated
	 */
	public EditPart getTargetEditPart(Request request) {
		if (request instanceof CreateViewAndElementRequest) {
			CreateElementRequestAdapter adapter = ((CreateViewAndElementRequest) request)
					.getViewAndElementDescriptor()
					.getCreateElementRequestAdapter();
			IElementType type = (IElementType) adapter
					.getAdapter(IElementType.class);
			if (type == EddElementTypes.Block_3001) {
				return this;
			}
			return getParent().getTargetEditPart(request);
		}
		if (request instanceof CreateUnspecifiedTypeConnectionRequest) {
			return getParent().getTargetEditPart(request);
		}
		return super.getTargetEditPart(request);
	}

}

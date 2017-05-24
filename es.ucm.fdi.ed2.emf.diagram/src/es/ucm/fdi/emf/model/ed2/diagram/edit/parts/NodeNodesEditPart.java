package es.ucm.fdi.emf.model.ed2.diagram.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

import es.ucm.fdi.emf.model.ed2.diagram.edit.policies.NodeNodesItemSemanticEditPolicy;

/**
 * @generated
 */
public class NodeNodesEditPart extends ConnectionNodeEditPart implements
		ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4002;

	/**
	 * @generated
	 */
	public NodeNodesEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new NodeNodesItemSemanticEditPolicy());
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */

	protected Connection createConnectionFigure() {
		return new NodeNodesFigure();
	}

	/**
	 * @generated
	 */
	public NodeNodesFigure getPrimaryShape() {
		return (NodeNodesFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class NodeNodesFigure extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		public NodeNodesFigure() {
			this.setLineStyle(Graphics.LINE_DASH);
			this.setBackgroundColor(THIS_BACK);

			setTargetDecoration(createTargetDecoration());
		}

		/**
		 * @generated
		 */
		private RotatableDecoration createTargetDecoration() {
			PolylineDecoration df = new PolylineDecoration();
			df.setBackgroundColor(ColorConstants.green);
			return df;
		}

	}

	/**
	 * @generated
	 */
	static final Color THIS_BACK = new Color(null, 64, 0, 64);

}

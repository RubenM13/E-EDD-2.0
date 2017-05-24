package es.ucm.fdi.emf.model.ed2.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import es.ucm.fdi.emf.model.ed2.TreeElement;
import es.ucm.fdi.emf.model.ed2.diagram.edit.policies.Ed2BaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class ED2TreeElementsReorientCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject referenceOwner;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public ED2TreeElementsReorientCommand(
			ReorientReferenceRelationshipRequest request) {
		super(request.getLabel(), null, request);
		reorientDirection = request.getDirection();
		referenceOwner = request.getReferenceOwner();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == referenceOwner instanceof es.ucm.fdi.emf.model.ed2.ED2) {
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean canReorientSource() {
		if (!(oldEnd instanceof TreeElement && newEnd instanceof es.ucm.fdi.emf.model.ed2.ED2)) {
			return false;
		}
		return Ed2BaseItemSemanticEditPolicy.getLinkConstraints()
				.canExistED2TreeElements_4001(getNewSource(), getOldTarget());
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof TreeElement && newEnd instanceof TreeElement)) {
			return false;
		}
		return Ed2BaseItemSemanticEditPolicy.getLinkConstraints()
				.canExistED2TreeElements_4001(getOldSource(), getNewTarget());
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException {
		getOldSource().getTreeElements().remove(getOldTarget());
		getNewSource().getTreeElements().add(getOldTarget());
		return CommandResult.newOKCommandResult(referenceOwner);
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getOldSource().getTreeElements().remove(getOldTarget());
		getOldSource().getTreeElements().add(getNewTarget());
		return CommandResult.newOKCommandResult(referenceOwner);
	}

	/**
	 * @generated
	 */
	protected es.ucm.fdi.emf.model.ed2.ED2 getOldSource() {
		return (es.ucm.fdi.emf.model.ed2.ED2) referenceOwner;
	}

	/**
	 * @generated
	 */
	protected es.ucm.fdi.emf.model.ed2.ED2 getNewSource() {
		return (es.ucm.fdi.emf.model.ed2.ED2) newEnd;
	}

	/**
	 * @generated
	 */
	protected TreeElement getOldTarget() {
		return (TreeElement) oldEnd;
	}

	/**
	 * @generated
	 */
	protected TreeElement getNewTarget() {
		return (TreeElement) newEnd;
	}
}

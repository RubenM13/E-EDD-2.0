package es.ucm.fdi.gmf.edd.diagram.edit.helpers;

import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

/**
 * @generated
 */
public class ModelEditHelper extends EddBaseEditHelper {

	/**
	 * @generated NOT
	 */
	protected ICommand getDestroyElementCommand(DestroyElementRequest req) {
		// FIXME Joel: Prevents from removing the figure
		return UnexecutableCommand.INSTANCE;
	}
}

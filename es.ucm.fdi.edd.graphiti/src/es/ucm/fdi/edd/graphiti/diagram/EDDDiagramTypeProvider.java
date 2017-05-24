package es.ucm.fdi.edd.graphiti.diagram;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

/**
 * Defines the diagram type provider (in Eclipse more or less a sub type of the Graphiti diagram editor) for the EDD diagram type.
 */
public class EDDDiagramTypeProvider extends AbstractDiagramTypeProvider implements IDiagramTypeProvider {

	private IToolBehaviorProvider[] toolBehaviorProviders;

	public EDDDiagramTypeProvider() {
		super();
		// The diagram type provider needs to know its feature provider, so the Graphiti framework can ask which operations are supported.
		setFeatureProvider(new EDDFeatureProvider(this));
	}

	@Override
	public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
		if (toolBehaviorProviders == null) {
			toolBehaviorProviders = new IToolBehaviorProvider[] { new EDDToolBehaviorProvider(this) };
		}
		return toolBehaviorProviders;
	}
}
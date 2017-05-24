package es.ucm.fdi.edd.graphiti.diagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.pattern.DefaultFeatureProviderWithPatterns;
import org.eclipse.graphiti.util.IPredefinedRenderingStyle;

import es.ucm.fdi.edd.graphiti.features.AddContainmentConnectionFeature;
import es.ucm.fdi.edd.graphiti.features.CreateContainmentConnectionFeature;
import es.ucm.fdi.edd.graphiti.features.CreateInnerNodeFeature;
import es.ucm.fdi.edd.graphiti.features.DeleteInnerNodeFeature;
import es.ucm.fdi.edd.graphiti.features.GradientColorFeature;
import es.ucm.fdi.edd.graphiti.patterns.EDDPattern;
import es.ucm.fdi.edd.graphiti.patterns.LeafPattern;
import es.ucm.fdi.edd.graphiti.patterns.NodePattern;
import es.ucm.fdi.edd.graphiti.patterns.TreeElementPattern;
import es.ucm.fdi.edd.graphiti.ui.EDDPredefinedColoredAreas;

/**
 * Is used by the Graphiti framework to find out which operations are supported
 * by this editor in the current situation.
 */
public class EDDFeatureProvider extends DefaultFeatureProviderWithPatterns {

	//private static final String EXTENSION = "edd";
	
	static List<String> ALL_GRADIENT_IDS = Arrays.asList(
			EDDPredefinedColoredAreas.GREEN_WHITE_ID,
			EDDPredefinedColoredAreas.RED_WHITE_ID, 
			IPredefinedRenderingStyle.BLUE_WHITE_ID,
			IPredefinedRenderingStyle.BLUE_WHITE_GLOSS_ID);

	public EDDFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
		addPattern(new EDDPattern());
		addPattern(new TreeElementPattern());
		addPattern(new NodePattern());
		addPattern(new LeafPattern());
	}

	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] { new CreateContainmentConnectionFeature(this) };
	}

	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		if (context instanceof IAddConnectionContext) {
			return new AddContainmentConnectionFeature(this);
		}
		return super.getAddFeature(context);
	}

	@Override
	public ICustomFeature[] getCustomFeatures(ICustomContext context) {
		ICustomFeature[] ret = super.getCustomFeatures(context);

		// Add features to change gradient
		List<ICustomFeature> retList = new ArrayList<ICustomFeature>();
		for (String gid : ALL_GRADIENT_IDS) {
			retList.add(new GradientColorFeature(this, gid));
		}

		// Add create/delete features for files inside folders
		retList.add(new CreateInnerNodeFeature(this));
		retList.add(new DeleteInnerNodeFeature(this));

		ret = retList.toArray(ret);
		return ret;
	}
}

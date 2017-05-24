package es.ucm.fdi.edd.graphiti.features;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;

import es.ucm.fdi.edd.graphiti.ui.EDDPredefinedColoredAreas;

/**
 * The Class GradientColorFeature.
 */
public class GradientColorFeature extends AbstractCustomFeature {

	private static final String DESCRIPTION = "Select Gradient";
	private final String gradientId;

	/**
	 * The Constructor.
	 * 
	 * @param fp
	 *            the fp
	 */
	public GradientColorFeature(IFeatureProvider fp, String gradientId) {
		super(fp);
		this.gradientId = gradientId;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION + ": " + gradientId;
	}

	@Override
	public String getName() {
		return gradientId;
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		AdaptedGradientColoredAreas coloredAreas = EDDPredefinedColoredAreas
				.getAdaptedGradientColoredAreas(gradientId);
		if (coloredAreas == null) {
			return false;
		}

		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length >= 1) {
			GraphicsAlgorithm ga = pes[0].getGraphicsAlgorithm();
			if (ga != null) {
				for (GraphicsAlgorithm innerGa : ga.getGraphicsAlgorithmChildren()) {
					if (innerGa == null) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	public void execute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length >= 1) {
			for (int i = 0; i < pes.length; i++) {
				PictogramElement pe = pes[i];
				GraphicsAlgorithm currentGa = pe.getGraphicsAlgorithm();

				// change color of inner GAs instead of the outer invsible one
				EList<GraphicsAlgorithm> gaChildren = currentGa.getGraphicsAlgorithmChildren();
				for (GraphicsAlgorithm innerGa : gaChildren) {
					// only the rectangles, not the text GA
					if (innerGa instanceof RoundedRectangle || innerGa instanceof Rectangle) {
						// each GA has to have colored areas of his own; reason:
						// aggregation in the metamodel
						AdaptedGradientColoredAreas ca = EDDPredefinedColoredAreas
								.getAdaptedGradientColoredAreas(gradientId);
						Graphiti.getGaService().setRenderingStyle(innerGa, ca);
					}
				}
			}
		}
	}
}
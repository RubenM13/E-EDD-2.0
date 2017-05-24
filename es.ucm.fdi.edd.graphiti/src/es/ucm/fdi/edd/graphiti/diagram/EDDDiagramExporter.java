package es.ucm.fdi.edd.graphiti.diagram;

import org.eclipse.draw2d.IFigure;
import org.eclipse.graphiti.ui.internal.util.ui.print.IDiagramsExporter;
import org.eclipse.swt.graphics.Image;

/**
 * Extending the Graphiti framework with an export option for EDD.
 */
@SuppressWarnings("restriction")
public class EDDDiagramExporter implements IDiagramsExporter {

	@Override
	public void export(Image im, IFigure figure, String fileName, Double scaleFactor) throws Exception {
		
	}
}
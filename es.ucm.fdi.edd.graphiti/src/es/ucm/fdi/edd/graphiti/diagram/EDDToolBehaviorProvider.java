package es.ucm.fdi.edd.graphiti.diagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;
import org.eclipse.graphiti.palette.impl.ConnectionCreationToolEntry;
import org.eclipse.graphiti.palette.impl.ObjectCreationToolEntry;
import org.eclipse.graphiti.palette.impl.PaletteCompartmentEntry;
import org.eclipse.graphiti.palette.impl.StackEntry;
import org.eclipse.graphiti.tb.ContextButtonEntry;
import org.eclipse.graphiti.tb.ContextMenuEntry;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonEntry;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IContextMenuEntry;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.util.ILocationInfo;
import org.eclipse.graphiti.util.LocationInfo;

import es.ucm.fdi.edd.emf.model.edd.TreeElement;
import es.ucm.fdi.edd.graphiti.features.CreateInnerNodeFeature;
import es.ucm.fdi.edd.graphiti.features.DeleteInnerNodeFeature;
import es.ucm.fdi.edd.graphiti.features.GradientColorFeature;

public class EDDToolBehaviorProvider extends DefaultToolBehaviorProvider implements IToolBehaviorProvider {

	public EDDToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	public ILocationInfo getLocationInfo(PictogramElement pe, ILocationInfo locationInfo) {
		Object domainObject = getFeatureProvider().getBusinessObjectForPictogramElement(pe);
		if (domainObject instanceof TreeElement && locationInfo != null) {
			return new LocationInfo((Shape) pe, locationInfo.getGraphicsAlgorithm());
		}
		return super.getLocationInfo(pe, locationInfo);
	}

	@Override
	public IContextMenuEntry[] getContextMenu(ICustomContext context) {
		IContextMenuEntry[] ret = NO_CONTEXT_MENU_ENTRIES;
		List<IContextMenuEntry> retList = new ArrayList<IContextMenuEntry>();
		// custom features
		ICustomContext customContext = context;
		ICustomFeature[] customFeatures = getFeatureProvider().getCustomFeatures(customContext);

		// Gradient colors submenu
		ContextMenuEntry changeGradientColorEntry = null;

		for (int i = 0; i < customFeatures.length; i++) {
			ICustomFeature customFeature = customFeatures[i];
			ContextMenuEntry contextMenuEntry = new ContextMenuEntry(customFeature, context);
			if (customFeature instanceof GradientColorFeature) {
				if (changeGradientColorEntry == null) {
					changeGradientColorEntry = new ContextMenuEntry(null, null);
					changeGradientColorEntry.setSubmenu(true);
					changeGradientColorEntry.setText("Gradient Color");
					changeGradientColorEntry.setDescription("Change Gradient Color");
					retList.add(changeGradientColorEntry);
				}
				changeGradientColorEntry.add(contextMenuEntry);
			} else if (customFeature instanceof CreateInnerNodeFeature) {
				retList.add(contextMenuEntry);
			} else if (customFeature instanceof DeleteInnerNodeFeature) {
				retList.add(contextMenuEntry);
			}
		}
		return retList.toArray(ret);
	}

	@Override
	public IContextButtonPadData getContextButtonPad(IPictogramElementContext context) {
		IContextButtonPadData data = super.getContextButtonPad(context);
		PictogramElement pe = context.getPictogramElement();

		CustomContext customContext = new CustomContext(new PictogramElement[] { pe });
		ICustomFeature[] customFeatures = getFeatureProvider().getCustomFeatures(customContext);
		for (int i = 0; i < customFeatures.length; i++) {
			ICustomFeature customFeature = customFeatures[i];
			if (customFeature instanceof CreateInnerNodeFeature) {
				IContextButtonEntry button = null;
				button = new ContextButtonEntry(customFeature, customContext);
				button.setText(customFeature.getName());
				button.setDescription(customFeature.getDescription());
				button.setIconId(EDDImageProvider.IMG_CREATE_NODE);
				data.getDomainSpecificContextButtons().add(button);
			} else if (customFeature instanceof DeleteInnerNodeFeature) {
				IContextButtonEntry button = null;
				button = new ContextButtonEntry(customFeature, customContext);
				button.setText(customFeature.getName());
				button.setDescription(customFeature.getDescription());
				button.setIconId(EDDImageProvider.IMG_DELETE_NODE);
				data.getDomainSpecificContextButtons().add(button);
			}
		}

		return data;
	}
	
	@Override
	public IPaletteCompartmentEntry[] getPalette() {
		List<IPaletteCompartmentEntry> ret = new ArrayList<IPaletteCompartmentEntry>();

		// add compartments from super class
		IPaletteCompartmentEntry[] superCompartments = super.getPalette();
		for (int i = 0; i < superCompartments.length; i++)
			ret.add(superCompartments[i]);

		// add new compartment at the end of the existing compartments
		PaletteCompartmentEntry compartmentEntry = new PaletteCompartmentEntry("Stacked", null); //$NON-NLS-1$
		ret.add(compartmentEntry);

		// add new stack entry to new compartment
		StackEntry stackEntry = new StackEntry("EObject", "EObject", null); //$NON-NLS-1$ //$NON-NLS-2$
		compartmentEntry.addToolEntry(stackEntry);

		// add all create-features to the new stack-entry
		IFeatureProvider featureProvider = getFeatureProvider();
		ICreateFeature[] createFeatures = featureProvider.getCreateFeatures();
		for (ICreateFeature cf : createFeatures) {
			ObjectCreationToolEntry objectCreationToolEntry = new ObjectCreationToolEntry(cf.getCreateName(), cf.getCreateDescription(),
					cf.getCreateImageId(), cf.getCreateLargeImageId(), cf);

			stackEntry.addCreationToolEntry(objectCreationToolEntry);
		}

		// add all create-connection-features to the new stack-entry
		ICreateConnectionFeature[] createConnectionFeatures = featureProvider.getCreateConnectionFeatures();
		for (ICreateConnectionFeature cf : createConnectionFeatures) {
			ConnectionCreationToolEntry connectionCreationToolEntry = new ConnectionCreationToolEntry(cf.getCreateName(),
					cf.getCreateDescription(), cf.getCreateImageId(), cf.getCreateLargeImageId());
			connectionCreationToolEntry.addCreateConnectionFeature(cf);
			stackEntry.addCreationToolEntry(connectionCreationToolEntry);
		}

		return ret.toArray(new IPaletteCompartmentEntry[ret.size()]);
	}
}

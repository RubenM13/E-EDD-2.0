package es.ucm.fdi.edd.ui.views.providers;

import java.util.ArrayList;

import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelItemProviderAdapterFactory;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

import es.ucm.fdi.emf.model.ed2.provider.Ed2ItemProviderAdapterFactory;

/**
 * This provides support for composing a factory for the <code>EDD</code> model
 * into a single factory serving the union of the model objects.
 */
public class MNComposedAdapterFactory {
	
	/** The composed adapter factory. */
    private static ComposedAdapterFactory mnCompAdapterFactory;

    /**
     * Gets the adapter factory.
     *
     * @return the adapter factory
     */
    public final static ComposedAdapterFactory getAdapterFactory() {
		if (mnCompAdapterFactory == null)
			mnCompAdapterFactory = new ComposedAdapterFactory(createFactoryList());

		return mnCompAdapterFactory;
	}

    /**
     * Create the factory.
     *
     * @return the factory
     */
    public final static ArrayList<AdapterFactory> createFactoryList() {
		ArrayList<AdapterFactory> factories = new ArrayList<AdapterFactory>();
		factories.add(new ResourceItemProviderAdapterFactory());
		factories.add(new EcoreItemProviderAdapterFactory());
		factories.add(new GenModelItemProviderAdapterFactory());
		factories.add(new ReflectiveItemProviderAdapterFactory());
		// EDD Factory
		factories.add(new Ed2ItemProviderAdapterFactory());
		
		return factories;
	}
}

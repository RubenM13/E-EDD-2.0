package es.ucm.fdi.edd.ui.views.utils;

import java.util.Map;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmf.runtime.notation.NotationPackage;

import es.ucm.fdi.emf.model.ed2.Ed2Package;

/**
 * 
 */
public class RegisterPackage {

	private static final String MODEL_EXTENSION = "ed2";
	private static final String XMI = "xmi";
	
	public final static ResourceSet resourceSet = new ResourceSetImpl();

	/**
	 * 
	 */
	public static void initializeMetamodel() {
		packageRegister();
	}

	/**
	 * 
	 */
	private static void packageRegister() {
		ed2PackageRegister();
		notationPackageRegister();
		registryResourceExtension();
	}

	/**
	 * 
	 */
	private static void ed2PackageRegister() {
		resourceSet.getPackageRegistry().put(
				Ed2Package.eNS_URI,
				Ed2Package.eINSTANCE);
	}
	
	private static void notationPackageRegister() {
		resourceSet.getPackageRegistry().put(
				NotationPackage.eNS_URI,
				NotationPackage.eINSTANCE);
	}

	/**
	 * 
	 */
	private static void registryResourceExtension() {
		Map<String, Object> extensionToFactoryMap = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
		extensionToFactoryMap.put(MODEL_EXTENSION, new XMIResourceFactoryImpl());
		extensionToFactoryMap.put(XMI, new XMIResourceFactoryImpl());
	}
}
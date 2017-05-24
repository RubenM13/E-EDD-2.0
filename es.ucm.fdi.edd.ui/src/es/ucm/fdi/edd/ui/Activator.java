package es.ucm.fdi.edd.ui;

import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import es.ucm.fdi.edd.ui.utils.LogUtils;
import es.ucm.fdi.edd.ui.views.utils.RegisterPackage;

/**
 * The activator class controls the plug-in life cycle
 */
/**
 * @author Joel
 *
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "es.ucm.fdi.edd.ui"; 

	// The shared instance
	private static Activator plugin;
	
	// Resource bundle.
	private ResourceBundle resourceBundle;
	
	private FormColors formColors;
	public static final String IMG_FORM_BG = "formBg"; 
	public static final String IMG_LARGE = "large"; 
	public static final String IMG_HORIZONTAL = "horizontal"; 
	public static final String IMG_VERTICAL = "vertical"; 
	public static final String IMG_SAMPLE = "sample"; 
	public static final String IMG_WIZBAN = "wizban"; 
	public static final String IMG_LINKTO_HELP = "linkto_help"; 
	public static final String IMG_HELP_TOPIC = "help_topic"; 
	public static final String IMG_CLOSE = "close"; 
	
	/**
	 * The constructor
	 */
	public Activator() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		RegisterPackage.initializeMetamodel();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		try {
			if (formColors != null) {
				formColors.dispose();
				formColors = null;
			}
		} finally {
			plugin = null;
			super.stop(context);
		}
	}
	
	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public static void logUnexpected(String message, Exception e) {
		LogUtils.logError(PLUGIN_ID, message, e);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#initializeImageRegistry(org.eclipse.jface.resource.ImageRegistry)
	 */
	protected void initializeImageRegistry(ImageRegistry registry) {
		registerImage(registry, IMG_FORM_BG, "form_banner.gif"); 
		registerImage(registry, IMG_LARGE, "large_image.gif"); 
		registerImage(registry, IMG_HORIZONTAL, "th_horizontal.gif"); 
		registerImage(registry, IMG_VERTICAL, "th_vertical.gif"); 
		registerImage(registry, IMG_SAMPLE, "sample.gif"); 
		registerImage(registry, IMG_WIZBAN, "newprj_wiz.gif"); 
		registerImage(registry, IMG_LINKTO_HELP, "linkto_help.gif"); 
		registerImage(registry, IMG_HELP_TOPIC, "help_topic.gif"); 
		registerImage(registry, IMG_CLOSE, "close_view.gif"); 
	}

	/**
	 * @param registry
	 * @param key
	 * @param fileName
	 */
	private void registerImage(ImageRegistry registry, String key, String fileName) {
		try {
			IPath path = new Path("icons/" + fileName); 
			URL url = FileLocator.find(getBundle(), path, null);
			if (url!=null) {
				ImageDescriptor desc = ImageDescriptor.createFromURL(url);
				registry.put(key, desc);
			}
		} catch (Exception e) {
			logUnexpected(e.getMessage(), e);
		}
	}

	public FormColors getFormColors(Display display) {
		if (formColors == null) {
			formColors = new FormColors(display);
			formColors.markShared();
		}
		return formColors;
	}
	
	/**
	 * Returns the workspace instance.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}
	
	/**
	 * Get the root of the workspace.
	 * 
	 * @return
	 */
	public static IWorkspaceRoot getRoot() {
		return getWorkspace().getRoot();
	}
	
	/**
	 * Gets the project given its name.
	 * 
	 * @param name
	 *			The name of project
	 * @return
	 */
	public static IProject getProject(String name) {
		return getRoot().getProject(name);
	}
	
	/**
	 * Get all projects in the workspace
	 * 
	 * @return
	 */
	public static IProject[] getProjects() {
		IProject[] projects = getRoot().getProjects();
		return projects;
	}
	
	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = Activator.getDefault().getResourceBundle();
		try {
			return (bundle != null ? bundle.getString(key) : key);
		} catch (MissingResourceException e) {
			return key;
		}
	}
	
	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
	
	/**
	 * @param key
	 * @return
	 */
	public Image getImage(String key) {
		return getImageRegistry().get(key);
	}

	/**
	 * @param key
	 * @return
	 */
	public ImageDescriptor getImageDescriptor(String key) {
		return getImageRegistry().getDescriptor(key);
	}
}
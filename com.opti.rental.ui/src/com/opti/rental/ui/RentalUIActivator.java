package com.opti.rental.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * The activator class controls the plug-in life cycle
 */
public class RentalUIActivator extends AbstractUIPlugin implements RentalUIConstants {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.opti.rental.ui"; //$NON-NLS-1$

	// The shared instance
	private static RentalUIActivator plugin;
	
	private Map<String, Palette> palettes = new HashMap<>();
	/**
	 * The constructor
	 */
	public RentalUIActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		getExtensionsAuickAccess();
		initPalette();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RentalUIActivator getDefault() {
		return plugin;
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		Bundle b = FrameworkUtil.getBundle(this.getClass());
		
		reg.put(IMG_CUSTOMER, ImageDescriptor.createFromURL(b.getEntry(IMG_CUSTOMER)));
		reg.put(IMG_RENTAL, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL)));
		reg.put(IMG_AGENCY, ImageDescriptor.createFromURL(b.getEntry(IMG_AGENCY)));
	}
	
	private void getExtensionsAuickAccess() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		for(IConfigurationElement e : reg.getConfigurationElementsFor("org.eclipse.ui.views")) {
			if(e.getName().equals("view")) {
				System.out.println("Plugin : " + e.getNamespaceIdentifier() + ", " + e.getAttribute("name"));
			}
		}
	}
	private void withPalette() {
	IExtensionRegistry reg = Platform.getExtensionRegistry();
	for(IConfigurationElement e : reg.getConfigurationElementsFor("org.eclipse.ui.views")) {
		if(e.getName().equals("view")) {
			System.out.println("Plugin : " + e.getNamespaceIdentifier() + ", " + e.getAttribute("name"));
		}
	}
	}
	
	private void initPalette() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		for(IConfigurationElement configElement : reg.getConfigurationElementsFor("com.opti.rental.ui.Palette")) {
			try {
				Palette palette = new Palette();
				palette.setId(configElement.getAttribute("id"));
				palette.setIdName(configElement.getAttribute("name"));
				palette.setProvider((IColorProvider) configElement.createExecutableExtension("paletteClass"));
				palettes.put(palette.getId(), palette);
				System.out.println("Added this palette: " + palette.getIdName());
			}catch (Exception e1) {
				System.err.println("Caught IOException: " + e1.getMessage());
				
			}
		}
	}

	public Map<String, Palette> getPaletteManager() {
		return palettes;
	}
}

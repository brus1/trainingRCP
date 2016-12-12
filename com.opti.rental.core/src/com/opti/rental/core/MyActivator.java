package com.opti.rental.core;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.helpers.RentalAgencyGenerator;

public class MyActivator implements BundleActivator {

	private static BundleContext context;
	
	private static RentalAgency agency; // = RentalAgencyGenerator.createSampleAgency();

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		MyActivator.context = bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		MyActivator.context = null;
	}

	public RentalAgency getAgency() {
		if (agency == null) {
			return RentalAgencyGenerator.createSampleAgency();
		}
		return agency;
	}
	
}

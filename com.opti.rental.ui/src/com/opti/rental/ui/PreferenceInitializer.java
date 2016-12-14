package com.opti.rental.ui;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;

public class PreferenceInitializer extends AbstractPreferenceInitializer implements RentalUIConstants{

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = RentalUIActivator.getDefault().getPreferenceStore();
		store.setDefault(PREF_CUSTOMER_COLOR, StringConverter.asString(new RGB(1, 18, 14)));
		store.setDefault(PREF_RENTAL_COLOR, StringConverter.asString(new RGB(3, 12, 11)));
		store.setDefault(PREF_RENTAL_OBJECT_COLOR, StringConverter.asString(new RGB(4, 15, 19)));
	}
	
	

}

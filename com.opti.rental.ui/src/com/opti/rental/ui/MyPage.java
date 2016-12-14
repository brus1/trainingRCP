package com.opti.rental.ui;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class MyPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage, RentalUIConstants {

	public MyPage() {
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		setDescription("Example of preferance page implementation");
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {
		addField(new ColorFieldEditor(PREF_CUSTOMER_COLOR, "Customer", getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_RENTAL_COLOR, "RentalAgency", getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_RENTAL_OBJECT_COLOR, "RentalObject", getFieldEditorParent()));	
	}
}

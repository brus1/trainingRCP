package com.opti.rental.ui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalObject;
import com.opti.rental.core.RentalCoreActivator;
import org.eclipse.swt.custom.SashForm;

public class RentalPropertyView extends ViewPart {

	private Label rentedObjectLabel;
	private Label customerLabel;
	private Group grpDates;
	private Label startDate;
	private Label lblNewLabel_1;
	private Label endDate;

	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		infoGroup.setText("INFORMATION");
		infoGroup.setLayout(new GridLayout(2, false));
		
		rentedObjectLabel = new Label(infoGroup, SWT.BORDER);
		
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.CENTER;
		rentedObjectLabel.setLayoutData(gd);
		// TODO Auto-generated method stub
		
		Label label = new Label(infoGroup, SWT.NONE);
		label.setText("Loue a");
		customerLabel = new Label(infoGroup, SWT.BORDER);
		
		grpDates = new Group(parent, SWT.NONE);
		grpDates.setText("Dates de location");
		grpDates.setData(new Object());
		grpDates.setLayout(new GridLayout(2, false));
		
		Label dateLabel = new Label(grpDates, SWT.NONE);
		dateLabel.setData(new Object());
		dateLabel.setText("du");
		
		startDate = new Label(grpDates, SWT.NONE);
		startDate.setText("New Label");
		
		lblNewLabel_1 = new Label(grpDates, SWT.NONE);
		lblNewLabel_1.setText("au.");
		
		 endDate = new Label(grpDates, SWT.NONE);
		endDate.setBounds(0, 0, 55, 15);
		endDate.setText("New Label");
	

		setRental(RentalCoreActivator.getAgency().getRentals().get(0)); 
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}
	
	public void setRental(Rental r) {
		rentedObjectLabel.setText(r.getRentedObject().getName());
		customerLabel.setText("John Wayne");
		endDate.setText("03.11.2010");
		startDate.setText("03.11.2000");
	}
}

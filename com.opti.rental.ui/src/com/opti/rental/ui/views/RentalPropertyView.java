package com.opti.rental.ui.views;

import java.util.Date;

import javax.activation.DataSource;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.opti.rental.core.RentalCoreActivator;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;

public class RentalPropertyView extends ViewPart implements ISelectionListener{

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
		setLabelAsDragSource(rentedObjectLabel);
		
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.CENTER;
		rentedObjectLabel.setLayoutData(gd);
		// TODO Auto-generated method stub
		
		Label label = new Label(infoGroup, SWT.NONE);
		label.setText("Loue a");
		customerLabel = new Label(infoGroup, SWT.BORDER);
		setLabelAsDragSource(customerLabel);
		
		grpDates = new Group(parent, SWT.NONE);
		grpDates.setText("Dates de location");
		grpDates.setData(new Date());
		grpDates.setLayout(new GridLayout(2, false));
		
		Label dateLabel = new Label(grpDates, SWT.NONE);
		dateLabel.setData(new Date());
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
		customerLabel.setText(r.getCustomer().getDisplayName());
		endDate.setText(r.getStartDate().toString());
		startDate.setText(r.getEndDate().toString());
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
	}
	
	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if(selection instanceof IStructuredSelection) {
			Object selected = ((IStructuredSelection) selection).getFirstElement();
			if(selected instanceof Rental) {
				setRental((Rental) selected);
			}
		}	
	}
	
	public void setLabelAsDragSource(final Label label) {
		DragSource source = new DragSource(label, DND.DROP_MOVE | DND.DROP_COPY);
		
		source.setTransfer(new Transfer[] {TextTransfer.getInstance() });
		
		source.addDragListener(new DragSourceAdapter() {
			public void dragSetData(DragSourceEvent event) {
				if(TextTransfer.getInstance().isSupportedType(event.dataType)) {
					event.data = label.getText();
				}
			}
		});
	}
}

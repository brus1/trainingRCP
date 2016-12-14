package com.opti.rental.ui.views;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;

public class CustomerView extends ViewPart implements ISelectionListener{

	private Label customerName;

	public CustomerView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		customerName = new Label(parent, SWT.NONE);
		customerName.setText("My Customer");
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}
	
	public void setCustomer(Customer r) {
		customerName.setText(r.getDisplayName());
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
			if(selected instanceof Customer) {
				setCustomer((Customer) selected);
			}
		}	
	}

}

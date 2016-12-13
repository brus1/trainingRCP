package com.opti.rental.ui;

import java.util.Collection;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider {

	private static final Object[] EMPTY_RESULTS = null;

	@Override
	public Object[] getElements(Object inputElement) {
		Object[] result = null;
		if(inputElement instanceof Collection<?>) {
			result =((Collection<?>) inputElement).toArray();
		}
		return result;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof RentalAgency) {
			RentalAgency a = (RentalAgency) parentElement;
			return new Node[] {
				new Node(a, Node.CUSTOMERS), new Node(a, Node.RENTALOBJ), new Node(a, Node.RENTALS)	
			};
		}else if (parentElement instanceof Node) {
			return ((Node) parentElement).getChildren();
		}
		return EMPTY_RESULTS;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getText(Object element) {
		if(element instanceof RentalAgency) {
			return ((RentalAgency) element).getName();
		} else if (element instanceof Customer) {
			return ((Customer) element).getDisplayName();
		} else if (element instanceof RentalObject) {
			return ((RentalObject) element).getName();
		}
		// TODO Auto-generated method stub
		return super.getText(element);
	}
	
	@Override
	public Image getImage(Object element) {
		if(element instanceof RentalAgency) {
			return RentalUIActivator.getDefault().getImageRegistry().get(RentalUIConstants.IMG_RENTAL);
		} else if (element instanceof Customer) {
			return RentalUIActivator.getDefault().getImageRegistry().get(RentalUIConstants.IMG_CUSTOMER);
		} else if (element instanceof RentalObject) {
			return RentalUIActivator.getDefault().getImageRegistry().get(RentalUIConstants.IMG_AGENCY);
		}
		// TODO Auto-generated method stub
		return super.getImage(element);
	}
	
	public class Node {
		public static final String CUSTOMERS = "customers";
		public static final String RENTALS = "rentals";
		public static final String RENTALOBJ = "objects";
		String label;
		RentalAgency agency;
		
		public Node(RentalAgency agency, String label) {
			this.agency = agency;
			this.label = label;
		}
		
		Object[] getChildren() {
			if(label == CUSTOMERS) {
				return agency.getCustomers().toArray();
			} else if (label == RENTALS){
				return agency.getRentals().toArray();
			} else if (label == RENTALOBJ) {
				return agency.getObjectsToRent().toArray();
			}
			return null;
		} 
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return label;
		}
	}

	@Override
	public Color getForeground(Object element) {
		if(element instanceof Customer) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN);
		} else if (element instanceof RentalAgency) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
		} else if (element instanceof RentalObject) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
		} 
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}
}

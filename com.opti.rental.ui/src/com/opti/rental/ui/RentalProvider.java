package com.opti.rental.ui;

import java.util.Collection;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;

public class RentalProvider extends LabelProvider implements ITreeContentProvider {

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
			RentalAgency parentAgency = (RentalAgency) parentElement;
			return parentAgency.getCustomers().toArray();
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
		}
		else if (element instanceof Customer) {
			return ((Customer) element).getDisplayName();
		}
		// TODO Auto-generated method stub
		return super.getText(element);
	}
}

package com.opti.rental.ui;

import java.util.Collection;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider, RentalUIConstants {

	private static final Object[] EMPTY_RESULTS = null;

	@Override
	public Object[] getElements(Object inputElement) {
		Object[] result = null;
		if (inputElement instanceof Collection<?>) {
			result = ((Collection<?>) inputElement).toArray();
		}
		return result;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof RentalAgency) {
			RentalAgency a = (RentalAgency) parentElement;
			return new Node[] { new Node(a, Node.CUSTOMERS), new Node(a, Node.RENTALOBJ), new Node(a, Node.RENTALS) };
		} else if (parentElement instanceof Node) {
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
		if (element instanceof RentalAgency) {
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
		if (element instanceof RentalAgency) {
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
			if (label == CUSTOMERS) {
				return agency.getCustomers().toArray();
			} else if (label == RENTALS) {
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((agency == null) ? 0 : agency.hashCode());
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (agency == null) {
				if (other.agency != null)
					return false;
			} else if (!agency.equals(other.agency))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			return true;
		}

		private RentalProvider getOuterType() {
			return RentalProvider.this;
		}
	}

	@Override
	public Color getForeground(Object element) {
		   String palId = RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_PALETTE);
		   Palette p = RentalUIActivator.getDefault().getPaletteManager().get(palId);
			if(p != null) {
				return p.getProvider().getForeground(element);
			} else {
				return null;
			}
	}

	@Override
	public Color getBackground(Object element) {
		 String palId = RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_PALETTE);
			Palette p = RentalUIActivator.getDefault().getPaletteManager().get(palId);
				if(p != null) {
					return p.getProvider().getBackground(element);
				} else {
					return null;
				}
	}

	private Color getPrefColor(String rgbKey) {
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();

		Color result = colorRegistry.get(rgbKey);
		if (result == null) {
			// Get value in pref store
			colorRegistry.put(rgbKey, StringConverter.asRGB(rgbKey));
			result = colorRegistry.get(rgbKey);
		}
		return result;

	}
}

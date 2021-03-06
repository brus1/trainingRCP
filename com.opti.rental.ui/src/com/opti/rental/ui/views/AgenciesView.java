package com.opti.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.RentalAgency;
import com.opti.rental.core.RentalCoreActivator;
import com.opti.rental.ui.RentalProvider;
import com.opti.rental.ui.RentalUIActivator;

public class AgenciesView extends ViewPart implements IPropertyChangeListener  {

	TreeViewer tv;
	
	@Override
	public void createPartControl(Composite parent) {
		tv = new TreeViewer(parent);
		RentalProvider provider = new RentalProvider();
		tv.setContentProvider(provider);
		tv.setLabelProvider(provider);
		Collection<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(RentalCoreActivator.getAgency());
		tv.setInput(agencies);
		tv.expandAll();
		getSite().setSelectionProvider(tv);
		
		MenuManager menuManager = new MenuManager();
		Menu menu = menuManager.createContextMenu(tv.getControl());
		tv.getControl().setMenu(menu);
		getSite().registerContextMenu(menuManager, tv);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public void init(IViewSite site, IMemento memento) throws PartInitException {
		super.init(site);
		RentalUIActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
	}
	
	@Override
	public void dispose() {
		RentalUIActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
		super.dispose();
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		tv.refresh();
		
	}
	
	

}

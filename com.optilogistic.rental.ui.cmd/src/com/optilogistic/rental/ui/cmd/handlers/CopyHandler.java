package com.optilogistic.rental.ui.cmd.handlers;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import com.opcoach.training.rental.Customer;

public class CopyHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Clipboard clipboard = new Clipboard(Display.getCurrent());
		String customer = null;
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if(currentSelection instanceof IStructuredSelection) {
			IStructuredSelection strSelection = (IStructuredSelection) currentSelection;
			Object selected = strSelection.getFirstElement();
			if(selected instanceof Customer) {
				customer = ((Customer) selected).getDisplayName();
							
				String rtfData = "{\\rtf1\\b\\i " + customer + "}";
				TextTransfer textTransfer = TextTransfer.getInstance();
				RTFTransfer rtfTransfer = RTFTransfer.getInstance();
				Transfer[] transfers = new Transfer[]{textTransfer, rtfTransfer};
				Object[] data = new Object[]{customer, rtfData};
				clipboard.setContents(data, transfers);
				clipboard.dispose();
			}
			
		}
		return null;

	}

}

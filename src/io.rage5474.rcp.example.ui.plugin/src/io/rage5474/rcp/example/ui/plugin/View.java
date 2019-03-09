package io.rage5474.rcp.example.ui.plugin;

import java.util.*;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILazyContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	public static final String ID = "io.rage5474.rcp.example.ui.plugin.view";

	private TableViewer viewer;
	private final List<String> elements = createInitialDataModel();
	
	private class StringLabelProvider extends ColumnLabelProvider {
		@Override
		public String getText(Object element) {
			return super.getText(element);
		}

		@Override
		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}

	}

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.VIRTUAL);
		viewer.getTable().setLinesVisible(true);
		viewer.setUseHashlookup(true);
		viewer.getTable().getVerticalBar().setVisible(false);

		TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
		column.setLabelProvider(new StringLabelProvider());

		viewer.getTable().getColumn(0).setWidth(200);
		
		viewer.setContentProvider(new ILazyContentProvider() {
			
			@Override
			public void updateElement(int index) {
				if (!viewer.isBusy())
	                viewer.replace(elements.get(index), index);
			}
		});
		
		// Provide the input to the ContentProvider
		viewer.setInput(null);
		viewer.setItemCount(elements.size());
	}


	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	private List<String> createInitialDataModel() {
		List<String> elements = new ArrayList<>();
		for(int i = 0; i < 100000; i++)
		{
			elements.add("Element " + (i + 1));
		}
		return elements;
	}
}
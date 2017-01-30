package io.rage5474.rcp.example.ui.plugin;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.ragedev.elasticsearch.REvent;
import io.rage5474.rcp.example.core.plugin.api.ImportDataService;
import io.rage5474.rcp.example.core.plugin.api.ImportDataServiceFactory;
import io.rage5474.rcp.example.core.plugin.api.ImportDataServiceListener;

public class View extends ViewPart implements ImportDataServiceListener {
	public static final String ID = "io.rage5474.rcp.example.ui.plugin.view";

	private final ImportDataService importDataService = ImportDataServiceFactory.createInstance();
	
	private TableViewer viewer;

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.VIRTUAL);
		viewer.getTable().setLinesVisible(true);

		TableViewerColumn idColumn = new TableViewerColumn(viewer, SWT.NONE);
		idColumn.getColumn().setText("ID");
		idColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				REvent event = (REvent) element;
				return "" + event.getId();
			}
		});
		
		TableViewerColumn valueColumn = new TableViewerColumn(viewer, SWT.NONE);
		valueColumn.getColumn().setText("Value");
		valueColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				REvent event = (REvent) element;
				return "" + event.getValue();
			}
		});


		viewer.getTable().getColumn(0).setWidth(200);
		viewer.getTable().getColumn(1).setWidth(200);
		viewer.setUseHashlookup(true);
		viewer.setContentProvider(new ElasticSearchDBContentProvider(viewer));
		viewer.setItemCount(0);
		importDataService.register(this);
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	@Override
	public void onImportedData(int numberOfMessages) {
		viewer.setItemCount(numberOfMessages);
	}
	
	@Override
	public void dispose() {
		importDataService.unregister(this);
		super.dispose();
	}

}
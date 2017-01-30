package io.rage5474.rcp.example.ui.plugin;

import org.eclipse.jface.viewers.ILazyContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.elasticsearch.client.transport.TransportClient;

import de.ragedev.elasticsearch.ElasticSearchDBHelper;
import de.ragedev.elasticsearch.REvent;

public class ElasticSearchDBContentProvider implements ILazyContentProvider {

	private TableViewer tableViewer;
	private TransportClient client;

	public ElasticSearchDBContentProvider(TableViewer tableViewer) {
		this.tableViewer = tableViewer;
		client = ElasticSearchDBHelper.openConnectionToLocalDB();
	}
	
	@Override
	public void dispose() {
		ElasticSearchDBHelper.closeLocalDB(client);
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	@Override
	public void updateElement(int index) {
		REvent event = ElasticSearchDBHelper.getEvent(client, "messages", "trace.events", index);
		tableViewer.replace(event, index);
	}
	
}

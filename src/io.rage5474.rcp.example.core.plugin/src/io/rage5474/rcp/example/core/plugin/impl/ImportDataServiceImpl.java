package io.rage5474.rcp.example.core.plugin.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import de.ragedev.elasticsearch.REvent;
import io.rage5474.rcp.example.core.plugin.api.ImportDataService;
import io.rage5474.rcp.example.core.plugin.api.ImportDataServiceListener;

public class ImportDataServiceImpl implements ImportDataService {

	private Set<ImportDataServiceListener> listeners = ConcurrentHashMap.newKeySet();

	@Override
	public void register(ImportDataServiceListener importDataServiceListener) {
		listeners.add(importDataServiceListener);
		notifyListeners();
	}

	@Override
	public void importData() {
		ElasticSearchDBWrapper.deleteMessagesIfExists();
		for(int i = 0; i < 10; i++ )
		{
			List<REvent> events = createEvents(i, "Message" + i);
			ElasticSearchDBWrapper.addEventsToIndex(events);
			System.out.println("Round " + i + " done.");
		}
		
		notifyListeners();
	}

	private void notifyListeners() {
		for(ImportDataServiceListener nextListener : listeners)
			nextListener.onImportedData((int) ElasticSearchDBWrapper.numberOfEvents());
	}

	private List<REvent> createEvents(int id,  String prefix) {
		List<REvent> events = new ArrayList<>(1000000);
		for(int i = 0; i < 1000000; i++ )
			events.add(new REvent((id * 1000000) + i  , 1000 + i, prefix + i, "trace.events"));
		return events;
	}

	@Override
	public void unregister(ImportDataServiceListener importDataServiceListener) {
		listeners.remove(importDataServiceListener);
	}

}

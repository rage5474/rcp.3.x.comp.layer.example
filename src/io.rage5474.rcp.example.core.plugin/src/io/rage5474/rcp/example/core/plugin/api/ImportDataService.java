package io.rage5474.rcp.example.core.plugin.api;

public interface ImportDataService {

	public void register(ImportDataServiceListener importDataServiceListener);
	
	public void importData();
	
	public void unregister(ImportDataServiceListener importDataServiceListener);
}

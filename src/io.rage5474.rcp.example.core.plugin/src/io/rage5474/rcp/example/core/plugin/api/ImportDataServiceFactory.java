package io.rage5474.rcp.example.core.plugin.api;

import io.rage5474.rcp.example.core.plugin.impl.ImportDataServiceImpl;

public class ImportDataServiceFactory {

	public static final ImportDataService INSTANCE = new ImportDataServiceImpl(); 
	
	public static ImportDataService createInstance()
	{
		return INSTANCE;
	}
	
	public static ImportDataService createInstance(ImportDataServiceListener listener)
	{
		INSTANCE.register(listener);
		return INSTANCE;
	}
	
}

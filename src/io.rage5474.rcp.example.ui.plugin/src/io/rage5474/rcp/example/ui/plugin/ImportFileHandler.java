package io.rage5474.rcp.example.ui.plugin;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;

import io.rage5474.rcp.example.core.plugin.api.ImportDataService;
import io.rage5474.rcp.example.core.plugin.api.ImportDataServiceFactory;

public class ImportFileHandler extends AbstractHandler implements IHandler {

	private final ImportDataService importDataService = ImportDataServiceFactory.createInstance();
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		importDataService.importData();
		return null;
	}

}

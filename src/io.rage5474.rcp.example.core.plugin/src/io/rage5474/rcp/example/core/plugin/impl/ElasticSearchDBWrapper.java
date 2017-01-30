package io.rage5474.rcp.example.core.plugin.impl;

import java.util.List;

import org.elasticsearch.client.transport.TransportClient;

import de.ragedev.elasticsearch.ElasticSearchDBHelper;
import de.ragedev.elasticsearch.REvent;

public class ElasticSearchDBWrapper {

	private static final String MESSAGES_INDEX_NAME = "messages";

	private static final TransportClient DB_CONNECTION = ElasticSearchDBHelper.openConnectionToLocalDB();

	public static void deleteMessagesIfExists() {
		if (ElasticSearchDBHelper.indexExists(DB_CONNECTION, MESSAGES_INDEX_NAME))
			ElasticSearchDBHelper.deleteIndex(DB_CONNECTION, MESSAGES_INDEX_NAME);
	}
	
	public static void addEventsToIndex(List<REvent> events)
	{
		ElasticSearchDBHelper.addEventsToIndex(DB_CONNECTION, MESSAGES_INDEX_NAME,
				 events);
	}
	
	public static long numberOfEvents()
	{
		return ElasticSearchDBHelper.numberOfEvents(DB_CONNECTION);
	}
}

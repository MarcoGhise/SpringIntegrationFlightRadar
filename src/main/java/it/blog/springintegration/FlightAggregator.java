package it.blog.springintegration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.messaging.Message;

public class FlightAggregator {

	private static final String FLIGHT_PATTERN = ",\"\\w+\":\\[\"\\w+\",\\d+\\.\\d+,\\d+.\\d+,\\d+,\\d+,\\d+,\"\\d+\",\"\\w+-\\w+\",\"\\w*\",\"\\D+\",\\d+,\"([A-Z]{3})\",\"([A-Z]{3})\"";
	
	private static final Pattern PATTERN = Pattern.compile(FLIGHT_PATTERN);
	
	public Map<String, Integer> aggregateFrom(List<Message<?>> reqlist) {

		System.out.println("Total messages to send: " + reqlist.size()); 
		
		Map<String, Integer> request = new HashMap<String, Integer>();
		
		for (Message<?> mess: reqlist) {
			
			String payload = (String)mess.getPayload();
			
			/*
			 * Parse the string
			 */
			Matcher m = PATTERN.matcher(payload);
			if (m.find()) {
				
				String key = m.group(1);
				
				if (request.containsKey(key))
				{
					Integer count = request.get(key);
					count++;		
					request.put(key, count);
				}
				else
					request.put(m.group(1), 1);
			}
		}
		
		return request;
	}
	
	public Map<String, Integer> aggregateTo(List<Message<?>> reqlist) {

		System.out.println("Total messages to send: " + reqlist.size()); 
		
		Map<String, Integer> request = new HashMap<String, Integer>();
		
		for (Message<?> mess: reqlist) {
			
			String payload = (String)mess.getPayload();
			
			/*
			 * Parse the string
			 */
			Matcher m = PATTERN.matcher(payload);
			if (m.find()) {
				
				String key = m.group(2);
				
				if (request.containsKey(key))
				{
					Integer count = request.get(key);
					count++;		
					request.put(key, count);
				}
				else
					request.put(m.group(2), 1);
			}
		}
		
		return request;
	}
}

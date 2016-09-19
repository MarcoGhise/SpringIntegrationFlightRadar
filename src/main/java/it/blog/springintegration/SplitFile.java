package it.blog.springintegration;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.messaging.Message;
import org.springframework.integration.splitter.AbstractMessageSplitter;

public class SplitFile extends AbstractMessageSplitter {

	@Override
	protected ArrayList<?> splitMessage(Message<?> message) {
		
		String file = (String)message.getPayload();
		
		String[] fileSplitted = file.split("\\r?\\n");
		
		ArrayList<?> messages = new ArrayList<>( Arrays.asList( fileSplitted ) );
		
		return messages;
	}
}

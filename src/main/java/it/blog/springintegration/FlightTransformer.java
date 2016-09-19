package it.blog.springintegration;

import java.util.Map;
import org.springframework.integration.annotation.Transformer;

public class FlightTransformer {

	@Transformer
	public String MapToString(Map<String, Integer> payload)
	{		
		StringBuilder output = new StringBuilder();
		
		for (String key : payload.keySet()) {
			output.append(key);
			output.append(",");
			output.append(payload.get(key));
			output.append("\r\n");
		  }

		return output.toString();
	}
}

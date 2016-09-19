package it.blog.springintegration;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.integration.file.DefaultFileNameGenerator;
import org.springframework.messaging.Message;

public class NowFileNameGenerator extends DefaultFileNameGenerator {
	
	private String suffix;
	
	public String generateFileName(Message<?> message) {
		return new SimpleDateFormat("yyyy_MM_dd_hh_mm").format(new Date()) + suffix + ".csv";
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}

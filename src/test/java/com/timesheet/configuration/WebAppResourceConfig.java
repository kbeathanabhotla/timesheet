package com.timesheet.configuration;
import org.glassfish.jersey.server.ResourceConfig;

public class WebAppResourceConfig extends ResourceConfig {

	public WebAppResourceConfig() {
		packages("com.timesheet");
	}
}
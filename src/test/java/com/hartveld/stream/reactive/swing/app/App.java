package com.hartveld.stream.reactive.swing.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws Exception {
		LOG.trace("Starting app ...");

		final RandomStringsService service = new RandomStringsService();

		final AppControl control = new AppControl(service);
		control.showFrame();

		LOG.trace("App ready.");
	}

}

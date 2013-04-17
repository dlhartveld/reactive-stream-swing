package com.hartveld.stream.reactive.swing.app;

import javax.swing.JFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws Exception {
		LOG.info("Starting app ...");

		final RandomStringsService service = new RandomStringsService();

		final AppControl control = new AppControl(service);
		control.showFrame();

		control.frame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		control.frame().window().closing()
//				.delay(1, TimeUnit.SECONDS)
//				.subscribe(event -> {
//					LOG.info("Exiting app ...");
//					System.exit(0);
//				});

		LOG.info("App ready.");
	}

}

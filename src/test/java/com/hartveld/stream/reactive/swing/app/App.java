package com.hartveld.stream.reactive.swing.app;

import com.hartveld.stream.reactive.swing.DefaultReactiveListModel;
import javax.swing.JFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws Exception {
		LOG.info("Starting app ...");

		final RandomStringsService service = new RandomStringsService();

		final DefaultReactiveListModel<String> model = new DefaultReactiveListModel<>();
		final AppFrame appFrame = new AppFrame(model);
		final AppControl control = new AppControl(appFrame, model, service);

		control.showFrame();

		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		control.frame().window().closing()
//				.delay(1, TimeUnit.SECONDS)
//				.subscribe(event -> {
//					LOG.info("Exiting app ...");
//					System.exit(0);
//				});

		LOG.info("App ready.");
	}

}

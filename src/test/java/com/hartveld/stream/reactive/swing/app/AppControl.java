package com.hartveld.stream.reactive.swing.app;

import static com.google.common.base.Preconditions.checkNotNull;

import com.hartveld.stream.reactive.concurrency.Schedulers;
import com.hartveld.stream.reactive.swing.DefaultReactiveListModel;
import com.hartveld.stream.reactive.swing.ReactiveListModel;
import javax.swing.SwingUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppControl {

	private static final Logger LOG = LoggerFactory.getLogger(AppControl.class);

	final AppFrame frame;
	final ReactiveListModel<String> model;

	public AppControl(final RandomStringsService service) throws Exception {
		checkNotNull(service, "service");

		this.model = new DefaultReactiveListModel<>();

		this.frame = new AppFrame(model);
		//frame.setDefaultCloseOperation(AppFrame.EXIT_ON_CLOSE);

		final AutoCloseable subscription = frame.go
				.observeOn(Schedulers.DEFAULT)
				.flatMap(click -> {
					LOG.trace("Clearing model contents ...");
					model.clear();

					LOG.trace("Preparing backend service ...");
					return service.retrieveSeveralRandomStrings();
				})
				.observeOn(Schedulers.EDT)
				.subscribe(model);

		frame.window.closing
				.subscribe(event -> {
					try {
						subscription.close();
					} catch (final Exception ex) { }
				});
	}

	public void showFrame() throws Exception {
		LOG.trace("Showing frame ...");

		SwingUtilities.invokeAndWait(() -> {
			frame.pack();
			frame.setVisible(true);
		});

		LOG.trace("Frame visible.");
	}

}

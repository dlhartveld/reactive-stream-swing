package com.hartveld.stream.reactive.swing.app;

import static com.google.common.base.Preconditions.checkNotNull;

import com.hartveld.stream.reactive.component.ReactiveListModel;
import com.hartveld.stream.reactive.concurrency.Schedulers;
import com.hartveld.stream.reactive.swing.AbstractFrameControl;
import com.hartveld.stream.reactive.swing.DefaultReactiveListModel;
import com.hartveld.stream.reactive.swing.ReactiveSwingFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppControl extends AbstractFrameControl {

	private static final Logger LOG = LoggerFactory.getLogger(AppControl.class);

	public final AppFrame frame;
	public final ReactiveListModel<String> model;

	public AppControl(final RandomStringsService service) throws Exception {
		checkNotNull(service, "service");

		this.model = new DefaultReactiveListModel<>();

		this.frame = new AppFrame(model);
		//frame.setDefaultCloseOperation(AppFrame.EXIT_ON_CLOSE);

		final AutoCloseable subscription = frame.go().clicks()
				.observeOn(Schedulers.DEFAULT)
				.flatMap(click -> {
					LOG.trace("Clearing model contents ...");
					model.clear();

					LOG.trace("Preparing backend service ...");
					return service.retrieveSeveralRandomStrings();
				})
				.observeOn(Schedulers.EDT)
				.subscribe(model);

		frame.window().closing()
				.subscribe(event -> {
					try {
						subscription.close();
					} catch (final Exception ex) { }
				});
	}

	@Override
	public final ReactiveSwingFrame frame() {
		return this.frame;
	}

}

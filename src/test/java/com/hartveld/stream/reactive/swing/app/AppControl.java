package com.hartveld.stream.reactive.swing.app;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.hartveld.stream.reactive.concurrency.Schedulers.defaultScheduler;
import static com.hartveld.stream.reactive.concurrency.Schedulers.eventQueueScheduler;

import com.hartveld.stream.reactive.component.ReactiveListModel;
import com.hartveld.stream.reactive.swing.AbstractFrameControl;
import com.hartveld.stream.reactive.swing.DefaultReactiveListModel;
import com.hartveld.stream.reactive.swing.ReactiveSwingFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppControl extends AbstractFrameControl {

	private static final Logger LOG = LoggerFactory.getLogger(AppControl.class);

	public final AppFrame appFrame;
	public final ReactiveListModel<String> model;

	public AppControl(final AppFrame appFrame, final DefaultReactiveListModel<String> model, final RandomStringsService service) throws Exception {
		checkNotNull(appFrame, "appFrame");
		checkNotNull(model, "model");
		checkNotNull(service, "service");

		this.model = model;

		this.appFrame = appFrame;
		//frame.setDefaultCloseOperation(AppFrame.EXIT_ON_CLOSE);

		final AutoCloseable subscription = this.appFrame.go().clicks()
				.observeOn(defaultScheduler())
				.flatMap(click -> {
					LOG.trace("Clearing model contents ...");
					model.clear();

					LOG.trace("Preparing backend service ...");
					return service.retrieveSeveralRandomStrings();
				})
				.observeOn(eventQueueScheduler())
				.subscribe(model);

		this.appFrame.window().closing()
				.subscribe(event -> {
					try {
						subscription.close();
					} catch (final Exception ex) { }
				});
	}

	@Override
	protected final ReactiveSwingFrame frame() {
		return this.appFrame;
	}

}

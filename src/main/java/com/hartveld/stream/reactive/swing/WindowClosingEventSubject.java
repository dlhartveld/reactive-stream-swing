package com.hartveld.stream.reactive.swing;

import static com.google.common.base.Preconditions.checkNotNull;

import com.hartveld.stream.reactive.Observer;
import com.hartveld.stream.reactive.subjects.EventSubject;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WindowClosingEventSubject extends EventSubject<WindowEvent, WindowListener> {

	private static final Logger LOG = LoggerFactory.getLogger(WindowClosingEventSubject.class);

	private final Window window;

	public WindowClosingEventSubject(final Window window) {
		checkNotNull(window, "window");

		this.window = window;
	}

	@Override
	protected WindowListener onSubscribe(final Observer<WindowEvent> observer) {
		LOG.trace("onSubscribe(): {}", observer);

		checkNotNull(observer, "observer");

		final WindowListener listener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				LOG.trace("windowClosing(): {}", e);
				onNext(e);
			}
		};

		window.addWindowListener(listener);

		return listener;
	}

	@Override
	protected void onClose(final WindowListener source) {
		LOG.trace("onClose(): {}", source);

		checkNotNull(source, "source");

		window.removeWindowListener(source);
	}

}

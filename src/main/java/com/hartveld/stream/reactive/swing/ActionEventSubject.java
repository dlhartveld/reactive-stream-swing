package com.hartveld.stream.reactive.swing;

import com.hartveld.stream.reactive.subjects.EventSubject;
import static com.google.common.base.Preconditions.checkNotNull;

import com.hartveld.stream.reactive.Observer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionEventSubject extends EventSubject<ActionEvent, ActionListener> {

	private static final Logger LOG = LoggerFactory.getLogger(ActionEventSubject.class);

	private final AbstractButton button;

	public ActionEventSubject(final AbstractButton button) {
		checkNotNull(button, "button");

		this.button = button;
	}

	@Override
	protected ActionListener onSubscribe(final Observer<ActionEvent> observer) {
		LOG.trace("onSubscribe(): {}", observer);

		final ActionListener listener = e -> onNext(e);

		LOG.trace("Adding action listener: {}", listener);
		this.button.addActionListener(listener);

		return listener;
	}

	@Override
	protected void onClose(final ActionListener listener) {
		LOG.trace("onClose(): {}", listener);

		checkNotNull(listener, "source");

		LOG.trace("Removing action listener: {}", listener);
		this.button.removeActionListener(listener);
	}

}

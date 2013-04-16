package com.hartveld.stream.reactive.awt;

import static com.google.common.base.Preconditions.checkNotNull;

import com.hartveld.stream.reactive.Observer;
import com.hartveld.stream.reactive.subjects.EventSubject;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MouseMovedSubject extends EventSubject<MouseEvent, MouseMotionListener> {

	private static final Logger LOG = LoggerFactory.getLogger(MouseMovedSubject.class);

	private final Component component;

	MouseMovedSubject(final Component component) {
		checkNotNull(component, "component");

		this.component = component;
	}

	@Override
	protected MouseMotionListener onSubscribe(final Observer<? super MouseEvent> observer) {
		LOG.trace("onSubscribe(): {}", observer);

		final MouseMotionAdapter listener = new MouseMotionAdapter() {
			   @Override
			   public void mouseMoved(final MouseEvent event) {
				   checkNotNull(event, "event");

				   onNext(event);
			   }
		   };

		component.addMouseMotionListener(listener);

		return listener;
	}

	@Override
	protected void onClose(final MouseMotionListener source) {
		LOG.trace("onClose(): {}", source);

		component.removeMouseMotionListener(source);
	}

}

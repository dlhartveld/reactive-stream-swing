package com.hartveld.stream.reactive.awt;

import static com.google.common.base.Preconditions.checkNotNull;

import com.hartveld.stream.reactive.Observer;
import com.hartveld.stream.reactive.subjects.EventSubject;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MouseDraggedSubject extends EventSubject<MouseEvent, MouseMotionAdapter> {

	private static final Logger LOG = LoggerFactory.getLogger(MouseDraggedSubject.class);

	private final Component component;

	MouseDraggedSubject(final Component component) {
		checkNotNull(component, "component");

		this.component = component;
	}

	@Override
	protected MouseMotionAdapter onSubscribe(final Observer<? super MouseEvent> observer) {
		LOG.trace("onSubscribe(): {}", observer);

		final MouseMotionAdapter listener = new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				LOG.trace("mouseDragged(): {}", e);
				onNext(e);
			}
		};

		component.addMouseMotionListener(listener);

		return listener;
	}

	@Override
	protected void onClose(final MouseMotionAdapter source) {
		LOG.trace("onClose(): {}", source);

		component.removeMouseMotionListener(source);
	}

}

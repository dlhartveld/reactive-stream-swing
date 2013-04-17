package com.hartveld.stream.reactive.awt;

import com.hartveld.stream.reactive.Observable;
import com.hartveld.stream.reactive.component.ReactiveMouse;
import java.awt.Component;
import java.awt.event.MouseEvent;

class MouseEvents implements ReactiveMouse {

	private final Observable<MouseEvent> moved;
	private final Observable<MouseEvent> dragged;

	MouseEvents(final Component component) {
		this.moved = new MouseMovedSubject(component);
		this.dragged = new MouseDraggedSubject(component);
	}

	@Override
	public Observable<MouseEvent> moved() {
		return this.moved;
	}

	@Override
	public Observable<MouseEvent> dragged() {
		return this.dragged;
	}

}

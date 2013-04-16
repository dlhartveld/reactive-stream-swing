package com.hartveld.stream.reactive.awt;

import com.hartveld.stream.reactive.Observable;
import java.awt.Component;
import java.awt.event.MouseEvent;

public class ComponentEvents {

	public final MouseEvents mouseEvents;

	public ComponentEvents(final Component component) {
		this.mouseEvents = new MouseEvents(component);
	}

	public static class MouseEvents {

		public final Observable<MouseEvent> moved;
		public final Observable<MouseEvent> dragged;

		private MouseEvents(final Component component) {
			this.moved = new MouseMovedSubject(component);
			this.dragged = new MouseDraggedSubject(component);
		}

	}

}

package com.hartveld.stream.reactive.awt;

import com.hartveld.stream.reactive.component.ReactiveComponent;
import java.awt.Component;

public class ComponentEvents implements ReactiveComponent {

	private final MouseEvents mouseEvents;

	public ComponentEvents(final Component component) {
		this.mouseEvents = new MouseEvents(component);
	}

	@Override
	public MouseEvents mouse() {
		return this.mouseEvents;
	}

}

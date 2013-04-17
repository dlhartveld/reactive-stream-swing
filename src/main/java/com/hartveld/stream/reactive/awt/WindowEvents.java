package com.hartveld.stream.reactive.awt;

import com.hartveld.stream.reactive.Observable;
import com.hartveld.stream.reactive.component.ReactiveWindow;
import java.awt.Window;
import java.awt.event.WindowEvent;

public class WindowEvents implements ReactiveWindow {

	private final WindowClosingEventSubject closing;

	public WindowEvents(final Window window) {
		this.closing = new WindowClosingEventSubject(window);
	}

	@Override
	public Observable<WindowEvent> closing() {
		return this.closing;
	}

}

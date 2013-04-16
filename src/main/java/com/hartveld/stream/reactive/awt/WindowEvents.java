package com.hartveld.stream.reactive.awt;

import com.hartveld.stream.reactive.Observable;
import java.awt.Window;
import java.awt.event.WindowEvent;

public class WindowEvents {

	public final Observable<WindowEvent> closing;

	public WindowEvents(final Window window) {
		this.closing = new WindowClosingEventSubject(window);
	}

}

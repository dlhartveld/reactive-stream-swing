package com.hartveld.stream.reactive.swing;

import static com.google.common.base.Preconditions.checkNotNull;

import com.hartveld.stream.reactive.Observable;
import java.awt.Window;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class JFrameEvents {

	public final WindowEvents window;

	public JFrameEvents(final JFrame frame) {
		checkNotNull(frame, "frame");

		this.window = new WindowEvents(frame);
	}

	public static class WindowEvents {

		public final Observable<WindowEvent> closing;

		private WindowEvents(final Window window) {
			checkNotNull(window, "window");

			this.closing = new WindowClosingEventSubject(window);
		}

	}

}

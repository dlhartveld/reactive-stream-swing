package com.hartveld.stream.reactive.swing;

import com.hartveld.stream.reactive.awt.ComponentEvents;
import com.hartveld.stream.reactive.awt.WindowEvents;
import com.hartveld.stream.reactive.component.ReactiveComponent;
import com.hartveld.stream.reactive.component.ReactiveFrame;
import com.hartveld.stream.reactive.component.ReactiveWindow;
import java.awt.GraphicsConfiguration;
import javax.swing.JFrame;

public class ReactiveSwingFrame extends JFrame implements ReactiveFrame {

	private final ComponentEvents component = new ComponentEvents(this);
	private final WindowEvents window = new WindowEvents(this);

	public ReactiveSwingFrame() {
		super();
	}

	public ReactiveSwingFrame(final String title) {
		super(title);
	}

	public ReactiveSwingFrame(final GraphicsConfiguration gc) {
		super(gc);
	}

	public ReactiveSwingFrame(final String title, final GraphicsConfiguration gc) {
		super(title, gc);
	}

	@Override
	public ReactiveComponent component() {
		return this.component;
	}

	@Override
	public ReactiveWindow window() {
		return this.window;
	}


}

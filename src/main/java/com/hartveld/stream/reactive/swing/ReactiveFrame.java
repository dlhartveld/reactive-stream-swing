package com.hartveld.stream.reactive.swing;

import com.hartveld.stream.reactive.awt.ComponentEvents;
import com.hartveld.stream.reactive.awt.WindowEvents;
import java.awt.GraphicsConfiguration;
import javax.swing.JFrame;

public class ReactiveFrame extends JFrame {

	public final ComponentEvents component = new ComponentEvents(this);
	public final WindowEvents window = new WindowEvents(this);

	public ReactiveFrame() {
		super();
	}

	public ReactiveFrame(final String title) {
		super(title);
	}

	public ReactiveFrame(final GraphicsConfiguration gc) {
		super(gc);
	}

	public ReactiveFrame(final String title, final GraphicsConfiguration gc) {
		super(title, gc);
	}

}

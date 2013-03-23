package com.hartveld.stream.reactive.swing;

import java.awt.GraphicsConfiguration;
import javax.swing.JFrame;

public class ReactiveFrame extends JFrame {

	public final JFrameEvents events = new JFrameEvents(this);

	public ReactiveFrame() {
		super();
	}

	public ReactiveFrame(String title) {
		super(title);
	}

	public ReactiveFrame(GraphicsConfiguration gc) {
		super(gc);
	}

	public ReactiveFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

}

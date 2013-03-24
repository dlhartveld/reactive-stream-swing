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

package com.hartveld.stream.reactive.swing;

import static java.awt.EventQueue.isDispatchThread;

import java.awt.EventQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractFrameControl {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractFrameControl.class);

	protected abstract ReactiveSwingFrame frame();

	public void showFrame() throws Exception {
		LOG.trace("Showing frame ...");

		if (isDispatchThread()) {
			doShowFrame();
		} else {
			EventQueue.invokeAndWait(() -> doShowFrame());
		}

		LOG.trace("Frame visible.");
	}

	private void doShowFrame() {
		frame().pack();
		frame().setVisible(true);
	}

}

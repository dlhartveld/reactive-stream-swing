package com.hartveld.stream.reactive.swing;

import javax.swing.SwingUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractFrameControl {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractFrameControl.class);

	public abstract ReactiveSwingFrame frame();

	public void showFrame() throws Exception {
		LOG.trace("Showing frame ...");

		SwingUtilities.invokeAndWait(() -> {
			frame().pack();
			frame().setVisible(true);
		});

		LOG.trace("Frame visible.");
	}

}
